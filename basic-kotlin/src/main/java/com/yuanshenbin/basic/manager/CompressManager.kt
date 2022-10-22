package com.yuanshenbin.basic.manager

import com.yuanshenbin.basic.config.BasicOptions.Companion.instance
import com.yuanshenbin.basic.constant.BasicConstants
import com.yuanshenbin.basic.manager.RxManager.add
import com.yuanshenbin.basic.model.FileCompressModel
import com.yuanshenbin.basic.util.Utils.Companion.isEmpty
import io.reactivex.Observable
import io.reactivex.Observer
import top.zibin.luban.Luban
import java.io.File

/**
 * author : yuanshenbin
 * time   : 2018/11/11
 * desc   : 图片压缩工具类
 */
class CompressManager(builder: Builder) {
    class Builder(internal val filePath: String) {
        var compressSize = BasicConstants.COMPRESS_SIZE
        var count = BasicConstants.COMPRESS_COUNT

        /**
         * 对部分图片需要做其他处理，例如图片加水印
         */
        internal var listener: onModifyFileListener? = null
        var observer: Observer<FileCompressModel>? = null
        fun count(count: Int): Builder {
            this.count = count
            return this
        }

        fun compressSize(compressSize: Int): Builder {
            this.compressSize = compressSize
            return this
        }

        fun callback(observer: Observer<FileCompressModel>?): Builder {
            this.observer = observer
            return this
        }

        fun modifyFile(listener: onModifyFileListener?): Builder {
            this.listener = listener
            return this
        }

        fun build(): CompressManager {
            return CompressManager(this)
        }

    }

    interface onModifyFileListener {
        fun onPath(filePath: String?): String?
    }

    init {
        add(Observable.create { e ->
            val model = FileCompressModel()
            val oldLength = File(builder.filePath).length() / 1024
            model.oldSize = oldLength
            model.oldPath = builder.filePath
            var filePath: String? = builder.filePath
            if (builder.listener != null) {
                filePath = builder.listener!!.onPath(filePath)
            }
            var imageFile = File(filePath)
            var count = 0
            //循环遍历设置大小的压缩，超过10次就不在压缩了，避免死循环,因为压缩是阔值，
            while (imageFile.length() > builder.compressSize * 1024 && count < builder.count) {
                count++
                imageFile = Luban.with(instance.context).ignoreBy(builder.compressSize)
                        .filter { path -> !isEmpty(path) && !path.toLowerCase().endsWith(".gif") }[imageFile.absolutePath]
            }
            val path = imageFile.absolutePath
            model.path = path
            model.size = File(path).length() / 1024
            e.onNext(model)
            e.onComplete()
        }, builder.observer)
    }
}