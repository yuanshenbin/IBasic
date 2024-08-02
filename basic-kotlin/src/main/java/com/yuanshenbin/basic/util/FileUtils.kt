package com.yuanshenbin.basic.util

import android.content.Context
import android.os.Build
import android.os.Environment
import com.yuanshenbin.basic.constant.BasicConstants
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
        fun getMimeType(file: File): String {
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
            for (i in BasicConstants.MIME_MAP.indices) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
                if (end == BasicConstants.MIME_MAP[i][0]) type = BasicConstants.MIME_MAP[i][1]
            }
            return type
        }

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