package com.yijiupi.core.basic.manager;

import com.yuanshenbin.basic.config.BasicOptions;
import com.yuanshenbin.basic.constant.BasicConstants;
import com.yuanshenbin.basic.manager.RxManager;
import com.yuanshenbin.basic.model.FileCompressModel;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import top.zibin.luban.Luban;

/**
 * author : yuanshenbin
 * time   : 2018/11/11
 * desc   : 图片压缩工具类
 */


public class CompressManager {
    public CompressManager(final Builder builder) {

        RxManager.add(Observable.create(new ObservableOnSubscribe<FileCompressModel>() {
            @Override
            public void subscribe(ObservableEmitter<FileCompressModel> e) throws Exception {

                FileCompressModel model = new FileCompressModel();
                long oldLength = new File(builder.filePath).length() / 1024;
                model.setOldSize(oldLength);
                model.setOldPath(builder.filePath);

                if (builder.listener != null) {
                    builder.filePath = builder.listener.onPath(builder.filePath);
                }
                File imageFile = new File(builder.filePath);
                int count = 0;
                //循环遍历设置大小的压缩，超过10次就不在压缩了，避免死循环,因为压缩是阔值，
                while (imageFile.length() > builder.compressSize * 1024 && count < builder.count) {
                    count++;
                    imageFile = Luban.with(BasicOptions.getInstance().getContext()).ignoreBy(builder.compressSize).load(imageFile).get(imageFile.getAbsolutePath());
                }
                String path = imageFile.getAbsolutePath();

                model.setPath(path);
                model.setSize(new File(path).length() / 1024);
                e.onNext(model);
                e.onComplete();

            }
        }), builder.observer);
    }

    public static final class Builder {

        private String filePath;
        private int compressSize = BasicConstants.COMPRESS_SIZE;
        private int count = BasicConstants.LUBAN_COUNT;
        /**
         * 对部分图片需要做其他处理，例如图片加水印
         */
        private onModifyFileListener listener;
        private Observer<FileCompressModel> observer;


        public Builder(String filePath) {
            this.filePath = filePath;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }


        public Builder compressSize(int compressSize) {
            this.compressSize = compressSize;
            return this;
        }

        public Builder callback(Observer<FileCompressModel> observer) {
            this.observer = observer;
            return this;
        }

        public Builder modifyFile(onModifyFileListener listener) {
            this.listener = listener;
            return this;
        }

        public CompressManager build() {
            return new CompressManager(this);
        }

    }

    public interface onModifyFileListener {

        String onPath(String filePath);
    }
}
