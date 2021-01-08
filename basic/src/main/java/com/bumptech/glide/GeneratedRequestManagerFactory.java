//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bumptech.glide;

import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory;
import com.yuanshenbin.basic.imgloader.GlideRequests;

final class GeneratedRequestManagerFactory implements RequestManagerFactory {
    GeneratedRequestManagerFactory() {
    }

    public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode treeNode) {
        return new GlideRequests(glide, lifecycle, treeNode);
    }
}
