package com.yuanshenbin.basic.manager;

import android.content.Context;
import android.content.Intent;

import com.yuanshenbin.basic.model.PreviewImgModel;
import com.yuanshenbin.basic.ui.activity.PreviewPicturesActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2021-11-08
 * desc   :
 */
public class PreviewManager {

    public PreviewManager(final Builder builder) {
        PreviewImgModel model = new PreviewImgModel();
        model.setIndex(builder.index);
        model.setPath(builder.urls);
        Intent intent = new Intent(builder.context, PreviewPicturesActivity.class);
        intent.putExtra(PreviewPicturesActivity.RESULT_PICTURES, model);
        builder.context.startActivity(intent);

    }

    public static final class Builder {
        private Context context;
        private List<String> urls = new ArrayList<>();
        private int index = 0;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder index(int index) {
            this.index = index;
            return this;
        }

        public Builder add(String url) {
            this.urls.add(url);
            return this;
        }

        public Builder addAll(List<String> urls) {
            this.urls.addAll(urls);
            return this;
        }

        public PreviewManager build() {
            return new PreviewManager(this);
        }
    }
}
