package com.yuanshenbin.basic.util

import android.content.Context
import android.os.Build
import android.os.Environment
import com.yuanshenbin.basic.util.DataClearManager.deleteAllFile
import com.yuanshenbin.basic.util.DataClearManager.getFolderSize
import java.io.File
import java.math.BigDecimal


/**
 * author : yuanshenbin
 * time   : 2017/12/21
 * desc   : 文件操作
 */
class FileUtils  {

    companion object {

        /**
         * 判断是否有SD卡
         */
        @JvmStatic
        val isExistSDCard: Boolean
            get() = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                Environment.getExternalStorageDirectory().canWrite()
            } else false

        /**
         * 返回手机内存路径优先内存卡
         *
         * @param path /com.xxx.xxx/files
         * @return
         */
        @JvmStatic
        fun getCachePath(context: Context, path: String): String {
            return if (isExistSDCard) {
                val file: File = if (Build.VERSION.SDK_INT >= 29) {
                    File(context.getExternalFilesDir("").toString() + File.separator + "Media" + path)
                } else {
                    File(Environment.getExternalStorageDirectory(), path)
                }
                if (!file.exists()) file.mkdirs()
                file.absolutePath + File.separator
            } else {
                val file = File(context.filesDir, path)
                if (!file.exists()) file.mkdirs()
                file.absolutePath + File.separator
            }
        }

        /**
         * 根据文件后缀名获得对应的MIME类型。
         *
         * @param file
         */
        @JvmStatic
        fun getMIMEType(file: File): String {
            var type = "*/*"
            val fName = file.name
            //获取后缀名前的分隔符"."在fName中的位置。
            val dotIndex = fName.lastIndexOf(".")
            if (dotIndex < 0) {
                return type
            }
            /* 获取文件的后缀名 */
            val end = fName.substring(dotIndex, fName.length).toLowerCase()
            if (end === "") return type
            //在MIME和文件类型的匹配表中找到对应的MIME类型。
            for (i in MIME_MapTable.indices) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
                if (end == MIME_MapTable[i][0]) type = MIME_MapTable[i][1]
            }
            return type
        }
        @JvmStatic
        var MIME_MapTable = arrayOf(arrayOf(".3gp", "video/3gpp"), arrayOf(".apk", "application/vnd.android.package-archive"), arrayOf(".asf", "video/x-ms-asf"), arrayOf(".avi", "video/x-msvideo"), arrayOf(".bin", "application/octet-stream"), arrayOf(".bmp", "image/bmp"), arrayOf(".c", "text/plain"), arrayOf(".class", "application/octet-stream"), arrayOf(".conf", "text/plain"), arrayOf(".cpp", "text/plain"), arrayOf(".doc", "application/msword"), arrayOf(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"), arrayOf(".xls", "application/vnd.ms-excel"), arrayOf(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"), arrayOf(".exe", "application/octet-stream"), arrayOf(".gif", "image/gif"), arrayOf(".gtar", "application/x-gtar"), arrayOf(".gz", "application/x-gzip"), arrayOf(".h", "text/plain"), arrayOf(".htm", "text/html"), arrayOf(".html", "text/html"), arrayOf(".jar", "application/java-archive"), arrayOf(".java", "text/plain"), arrayOf(".jpeg", "image/jpeg"), arrayOf(".jpg", "image/jpeg"), arrayOf(".js", "application/x-javascript"), arrayOf(".log", "text/plain"), arrayOf(".m3u", "audio/x-mpegurl"), arrayOf(".m4a", "audio/mp4a-latm"), arrayOf(".m4b", "audio/mp4a-latm"), arrayOf(".m4p", "audio/mp4a-latm"), arrayOf(".m4u", "video/vnd.mpegurl"), arrayOf(".m4v", "video/x-m4v"), arrayOf(".mov", "video/quicktime"), arrayOf(".mp2", "audio/x-mpeg"), arrayOf(".mp3", "audio/x-mpeg"), arrayOf(".mp4", "video/mp4"), arrayOf(".mpc", "application/vnd.mpohun.certificate"), arrayOf(".mpe", "video/mpeg"), arrayOf(".mpeg", "video/mpeg"), arrayOf(".mpg", "video/mpeg"), arrayOf(".mpg4", "video/mp4"), arrayOf(".mpga", "audio/mpeg"), arrayOf(".msg", "application/vnd.ms-outlook"), arrayOf(".ogg", "audio/ogg"), arrayOf(".pdf", "application/pdf"), arrayOf(".png", "image/png"), arrayOf(".pps", "application/vnd.ms-powerpoint"), arrayOf(".ppt", "application/vnd.ms-powerpoint"), arrayOf(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"), arrayOf(".prop", "text/plain"), arrayOf(".rc", "text/plain"), arrayOf(".rmvb", "audio/x-pn-realaudio"), arrayOf(".rtf", "application/rtf"), arrayOf(".sh", "text/plain"), arrayOf(".tar", "application/x-tar"), arrayOf(".tgz", "application/x-compressed"), arrayOf(".txt", "text/plain"), arrayOf(".wav", "audio/x-wav"), arrayOf(".wma", "audio/x-ms-wma"), arrayOf(".wmv", "audio/x-ms-wmv"), arrayOf(".wps", "application/vnd.ms-works"), arrayOf(".xml", "text/plain"), arrayOf(".z", "application/x-compress"), arrayOf(".zip", "application/x-zip-compressed"), arrayOf("", "*/*"))

        /**
         * 获取指定文件夹内所有文件大小的和
         *
         * @param file file
         * @return size
         * @throws Exception
         */
        @JvmStatic
        @Throws(Exception::class)
        private fun getFolderSize(file: File): Long {
            var size: Long = 0
            try {
                val fileList = file.listFiles()
                for (aFileList in fileList) {
                    size = if (aFileList.isDirectory) {
                        size + getFolderSize(aFileList)
                    } else {
                        size + aFileList.length()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return size
        }

        @JvmStatic
        @Throws(Exception::class)
        fun getCacheSize(file: File): String {
            return getFormatSize(getFolderSize(file).toDouble())
        }
        @JvmStatic
        @Throws(Exception::class)
        fun getFileSize(file: File): String {
            return getFormatSize(file.length().toDouble())
        }

        /**
         * 格式化单位
         *
         * @param size size
         * @return size
         */
        @JvmStatic
         fun getFormatSize(size: Double): String {
            val kiloByte = size / 1024
            if (kiloByte < 1) {
                return size.toString() + "k"
            }
            val megaByte = kiloByte / 1024
            if (megaByte < 1) {
                val result1 = BigDecimal(kiloByte.toString())
                return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB"
            }
            val gigaByte = megaByte / 1024
            if (gigaByte < 1) {
                val result2 = BigDecimal(megaByte.toString())
                return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB"
            }
            val teraBytes = gigaByte / 1024
            if (teraBytes < 1) {
                val result3 = BigDecimal(gigaByte.toString())
                return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB"
            }
            val result4 = BigDecimal(teraBytes)
            return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
        }


        @Throws(java.lang.Exception::class)
        @JvmStatic
        fun deleteAllFile(file: File) {
            try {
                val fileList = file.listFiles()
                for (i in fileList.indices) {
                    // 如果下面还有文件
                    if (fileList[i].isDirectory) {
                        deleteAllFile(fileList[i])
                    } else {
                        fileList[i].delete()
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        /**
         * 删除app应用所有缓存
         */
        @Throws(java.lang.Exception::class)
        @JvmStatic
        fun deleteAll(context: Context) {
            deleteAllFile(context.cacheDir)
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                deleteAllFile(context.externalCacheDir)
            }
        }

        /**
         * 获得所有缓存数据的大小
         *
         * @param context
         * @return
         * @throws Exception
         */
        @Throws(java.lang.Exception::class)
        fun getTotalCacheSize(context: Context): String? {
            var cacheSize = getFolderSize(context.cacheDir)
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                cacheSize += getFolderSize(context.externalCacheDir)
            }
            return getFormatSize(cacheSize.toDouble())
        }

    }
}