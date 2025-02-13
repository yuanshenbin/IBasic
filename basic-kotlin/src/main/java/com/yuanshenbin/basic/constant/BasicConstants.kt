package com.yuanshenbin.basic.constant

import com.yuanshenbin.basic.config.BasicOptions

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */

class BasicConstants {


    companion object {

        const val LOADING_STATE1 = 1 //没有数据
        const val LOADING_STATE2 = 2 //分页 不需要全局loading//或者有数据下啦也不需要loading
        const val LOADING_STATE3 = 3 //有数据加载无背景的loading
        const val LOADING_STATE4 = 4 //有数据下啦，没有loading

        @JvmField
        val PAGESIZE: Int = BasicOptions.instance.pageSize

        @JvmField
        val COMPRESS_COUNT: Int = BasicOptions.instance.compressCount

        @JvmField
        val COMPRESS_SIZE: Int = BasicOptions.instance.compressSize
        const val DEFAULT = "default"
        const val DEFAULT_ERROR = "default_error" //rxjava会用到区分请求和自己抛出异常

        val MIME_MAP = arrayOf(arrayOf(".323", "text/h323"),
                arrayOf(".3gp", "video/3gpp"),
                arrayOf(".aab", "application/x-authoware-bin"),
                arrayOf(".aam", "application/x-authoware-map"),
                arrayOf(".aas", "application/x-authoware-seg"),
                arrayOf(".acx", "application/internet-property-stream"),
                arrayOf(".ai", "application/postscript"),
                arrayOf(".aif", "audio/x-aiff"),
                arrayOf(".aifc", "audio/x-aiff"),
                arrayOf(".aiff", "audio/x-aiff"),
                arrayOf(".als", "audio/X-Alpha5"),
                arrayOf(".amc", "application/x-mpeg"),
                arrayOf(".ani", "application/octet-stream"),
                arrayOf(".apk", "application/vnd.android.package-archive"),
                arrayOf(".asc", "text/plain"),
                arrayOf(".asd", "application/astound"),
                arrayOf(".asf", "video/x-ms-asf"),
                arrayOf(".asn", "application/astound"),
                arrayOf(".asp", "application/x-asap"),
                arrayOf(".asr", "video/x-ms-asf"),
                arrayOf(".asx", "video/x-ms-asf"),
                arrayOf(".au", "audio/basic"),
                arrayOf(".avb", "application/octet-stream"),
                arrayOf(".avi", "video/x-msvideo"),
                arrayOf(".awb", "audio/amr-wb"),
                arrayOf(".axs", "application/olescript"),
                arrayOf(".bas", "text/plain"),
                arrayOf(".bcpio", "application/x-bcpio"),
                arrayOf(".bin ", "application/octet-stream"),
                arrayOf(".bld", "application/bld"),
                arrayOf(".bld2", "application/bld2"),
                arrayOf(".bmp", "image/bmp"),
                arrayOf(".bpk", "application/octet-stream"),
                arrayOf(".bz2", "application/x-bzip2"),
                arrayOf(".c", "text/plain"),
                arrayOf(".cal", "image/x-cals"),
                arrayOf(".cat", "application/vnd.ms-pkiseccat"),
                arrayOf(".ccn", "application/x-cnc"),
                arrayOf(".cco", "application/x-cocoa"),
                arrayOf(".cdf", "application/x-cdf"),
                arrayOf(".cer", "application/x-x509-ca-cert"),
                arrayOf(".cgi", "magnus-internal/cgi"),
                arrayOf(".chat", "application/x-chat"),
                arrayOf(".class", "application/octet-stream"),
                arrayOf(".clp", "application/x-msclip"),
                arrayOf(".cmx", "image/x-cmx"),
                arrayOf(".co", "application/x-cult3d-object"),
                arrayOf(".cod", "image/cis-cod"),
                arrayOf(".conf", "text/plain"),
                arrayOf(".cpio", "application/x-cpio"),
                arrayOf(".cpp", "text/plain"),
                arrayOf(".cpt", "application/mac-compactpro"),
                arrayOf(".crd", "application/x-mscardfile"),
                arrayOf(".crl", "application/pkix-crl"),
                arrayOf(".crt", "application/x-x509-ca-cert"),
                arrayOf(".csh", "application/x-csh"),
                arrayOf(".csm", "chemical/x-csml"),
                arrayOf(".csml", "chemical/x-csml"),
                arrayOf(".css", "text/css"),
                arrayOf(".cur", "application/octet-stream"),
                arrayOf(".dcm", "x-lml/x-evm"),
                arrayOf(".dcr", "application/x-director"),
                arrayOf(".dcx", "image/x-dcx"),
                arrayOf(".der", "application/x-x509-ca-cert"),
                arrayOf(".dhtml", "text/html"),
                arrayOf(".dir", "application/x-director"),
                arrayOf(".dll", "application/x-msdownload"),
                arrayOf(".dmg", "application/octet-stream"),
                arrayOf(".dms", "application/octet-stream"),
                arrayOf(".doc", "application/msword"),
                arrayOf(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
                arrayOf(".dot", "application/msword"),
                arrayOf(".dvi", "application/x-dvi"),
                arrayOf(".dwf", "drawing/x-dwf"),
                arrayOf(".dwg", "application/x-autocad"),
                arrayOf(".dxf", "application/x-autocad"),
                arrayOf(".dxr", "application/x-director"),
                arrayOf(".ebk", "application/x-expandedbook"),
                arrayOf(".emb", "chemical/x-embl-dl-nucleotide"),
                arrayOf(".embl", "chemical/x-embl-dl-nucleotide"),
                arrayOf(".eps", "application/postscript"),
                arrayOf(".epub", "application/epub+zip"),
                arrayOf(".eri", "image/x-eri"),
                arrayOf(".es", "audio/echospeech"),
                arrayOf(".esl", "audio/echospeech"),
                arrayOf(".etc", "application/x-earthtime"),
                arrayOf(".etx", "text/x-setext"),
                arrayOf(".evm", "x-lml/x-evm"),
                arrayOf(".evy", "application/envoy"),
                arrayOf(".exe", "application/octet-stream"),
                arrayOf(".fh4", "image/x-freehand"),
                arrayOf(".fh5", "image/x-freehand"),
                arrayOf(".fhc", "image/x-freehand"),
                arrayOf(".fif", "application/fractals"),
                arrayOf(".flr", "x-world/x-vrml"),
                arrayOf(".flv", "flv-application/octet-stream"),
                arrayOf(".fm", "application/x-maker"),
                arrayOf(".fpx", "image/x-fpx"),
                arrayOf(".fvi", "video/isivideo"),
                arrayOf(".gau", "chemical/x-gaussian-input"),
                arrayOf(".gca", "application/x-gca-compressed"),
                arrayOf(".gdb", "x-lml/x-gdb"),
                arrayOf(".gif", "image/gif"),
                arrayOf(".gps", "application/x-gps"),
                arrayOf(".gtar", "application/x-gtar"),
                arrayOf(".gz", "application/x-gzip"),
                arrayOf(".h", "text/plain"),
                arrayOf(".hdf", "application/x-hdf"),
                arrayOf(".hdm", "text/x-hdml"),
                arrayOf(".hdml", "text/x-hdml"),
                arrayOf(".hlp", "application/winhlp"),
                arrayOf(".hqx", "application/mac-binhex40"),
                arrayOf(".hta", "application/hta"),
                arrayOf(".htc", "text/x-component"),
                arrayOf(".htm", "text/html"),
                arrayOf(".html", "text/html"),
                arrayOf(".hts", "text/html"),
                arrayOf(".htt", "text/webviewhtml"),
                arrayOf(".ice", "x-conference/x-cooltalk"),
                arrayOf(".ico", "image/x-icon"),
                arrayOf(".ief", "image/ief"),
                arrayOf(".ifm", "image/gif"),
                arrayOf(".ifs", "image/ifs"),
                arrayOf(".iii", "application/x-iphone"),
                arrayOf(".imy", "audio/melody"),
                arrayOf(".ins", "application/x-internet-signup"),
                arrayOf(".ips", "application/x-ipscript"),
                arrayOf(".ipx", "application/x-ipix"),
                arrayOf(".isp", "application/x-internet-signup"),
                arrayOf(".it", "audio/x-mod"),
                arrayOf(".itz", "audio/x-mod"),
                arrayOf(".ivr", "i-world/i-vrml"),
                arrayOf(".j2k", "image/j2k"),
                arrayOf(".jad", "text/vnd.sun.j2me.app-descriptor"),
                arrayOf(".jam", "application/x-jam"),
                arrayOf(".jar", "application/java-archive"),
                arrayOf(".java", "text/plain"),
                arrayOf(".jfif", "image/pipeg"),
                arrayOf(".jnlp", "application/x-java-jnlp-file"),
                arrayOf(".jpe", "image/jpeg"),
                arrayOf(".jpeg", "image/jpeg"),
                arrayOf(".jpg", "image/jpeg"),
                arrayOf(".jpz", "image/jpeg"),
                arrayOf(".js", "application/x-javascript"),
                arrayOf(".jwc", "application/jwc"),
                arrayOf(".kjx", "application/x-kjx"),
                arrayOf(".lak", "x-lml/x-lak"),
                arrayOf(".latex", "application/x-latex"),
                arrayOf(".lcc", "application/fastman"),
                arrayOf(".lcl", "application/x-digitalloca"),
                arrayOf(".lcr", "application/x-digitalloca"),
                arrayOf(".lgh", "application/lgh"),
                arrayOf(".lha", "application/octet-stream"),
                arrayOf(".lml", "x-lml/x-lml"),
                arrayOf(".lmlpack", "x-lml/x-lmlpack"),
                arrayOf(".log", "text/plain"),
                arrayOf(".lsf", "video/x-la-asf"),
                arrayOf(".lsx", "video/x-la-asf"),
                arrayOf(".lzh", "application/octet-stream"),
                arrayOf(".m13", "application/x-msmediaview"),
                arrayOf(".m14", "application/x-msmediaview"),
                arrayOf(".m15", "audio/x-mod"),
                arrayOf(".m3u", "audio/x-mpegurl"),
                arrayOf(".m3url", "audio/x-mpegurl"),
                arrayOf(".m4a", "audio/mp4a-latm"),
                arrayOf(".m4b", "audio/mp4a-latm"),
                arrayOf(".m4p", "audio/mp4a-latm"),
                arrayOf(".m4u", "video/vnd.mpegurl"),
                arrayOf(".m4v", "video/x-m4v"),
                arrayOf(".ma1", "audio/ma1"),
                arrayOf(".ma2", "audio/ma2"),
                arrayOf(".ma3", "audio/ma3"),
                arrayOf(".ma5", "audio/ma5"),
                arrayOf(".man", "application/x-troff-man"),
                arrayOf(".map", "magnus-internal/imagemap"),
                arrayOf(".mbd", "application/mbedlet"),
                arrayOf(".mct", "application/x-mascot"),
                arrayOf(".mdb", "application/x-msaccess"),
                arrayOf(".mdz", "audio/x-mod"),
                arrayOf(".me", "application/x-troff-me"),
                arrayOf(".mel", "text/x-vmel"),
                arrayOf(".mht", "message/rfc822"),
                arrayOf(".mhtml", "message/rfc822"),
                arrayOf(".mi", "application/x-mif"),
                arrayOf(".mid", "audio/mid"),
                arrayOf(".midi", "audio/midi"),
                arrayOf(".mif", "application/x-mif"),
                arrayOf(".mil", "image/x-cals"),
                arrayOf(".mio", "audio/x-mio"),
                arrayOf(".mmf", "application/x-skt-lbs"),
                arrayOf(".mng", "video/x-mng"),
                arrayOf(".mny", "application/x-msmoney"),
                arrayOf(".moc", "application/x-mocha"),
                arrayOf(".mocha", "application/x-mocha"),
                arrayOf(".mod", "audio/x-mod"),
                arrayOf(".mof", "application/x-yumekara"),
                arrayOf(".mol", "chemical/x-mdl-molfile"),
                arrayOf(".mop", "chemical/x-mopac-input"),
                arrayOf(".mov", "video/quicktime"),
                arrayOf(".movie", "video/x-sgi-movie"),
                arrayOf(".mp2", "video/mpeg"),
                arrayOf(".mp3", "audio/mpeg"),
                arrayOf(".mp4", "video/mp4"),
                arrayOf(".mpa", "video/mpeg"),
                arrayOf(".mpc", "application/vnd.mpohun.certificate"),
                arrayOf(".mpe", "video/mpeg"),
                arrayOf(".mpeg", "video/mpeg"),
                arrayOf(".mpg", "video/mpeg"),
                arrayOf(".mpg4", "video/mp4"),
                arrayOf(".mpga", "audio/mpeg"),
                arrayOf(".mpn", "application/vnd.mophun.application"),
                arrayOf(".mpp", "application/vnd.ms-project"),
                arrayOf(".mps", "application/x-mapserver"),
                arrayOf(".mpv2", "video/mpeg"),
                arrayOf(".mrl", "text/x-mrml"),
                arrayOf(".mrm", "application/x-mrm"),
                arrayOf(".ms", "application/x-troff-ms"),
                arrayOf(".msg", "application/vnd.ms-outlook"),
                arrayOf(".mts", "application/metastream"),
                arrayOf(".mtx", "application/metastream"),
                arrayOf(".mtz", "application/metastream"),
                arrayOf(".mvb", "application/x-msmediaview"),
                arrayOf(".mzv", "application/metastream"),
                arrayOf(".nar", "application/zip"),
                arrayOf(".nbmp", "image/nbmp"),
                arrayOf(".nc", "application/x-netcdf"),
                arrayOf(".ndb", "x-lml/x-ndb"),
                arrayOf(".ndwn", "application/ndwn"),
                arrayOf(".nif", "application/x-nif"),
                arrayOf(".nmz", "application/x-scream"),
                arrayOf(".nokia-op-logo", "image/vnd.nok-oplogo-color"),
                arrayOf(".npx", "application/x-netfpx"),
                arrayOf(".nsnd", "audio/nsnd"),
                arrayOf(".nva", "application/x-neva1"),
                arrayOf(".nws", "message/rfc822"),
                arrayOf(".oda", "application/oda"),
                arrayOf(".ogg", "audio/ogg"),
                arrayOf(".oom", "application/x-AtlasMate-Plugin"),
                arrayOf(".p10", "application/pkcs10"),
                arrayOf(".p12", "application/x-pkcs12"),
                arrayOf(".p7b", "application/x-pkcs7-certificates"),
                arrayOf(".p7c", "application/x-pkcs7-mime"),
                arrayOf(".p7m", "application/x-pkcs7-mime"),
                arrayOf(".p7r", "application/x-pkcs7-certreqresp"),
                arrayOf(".p7s", "application/x-pkcs7-signature"),
                arrayOf(".pac", "audio/x-pac"),
                arrayOf(".pae", "audio/x-epac"),
                arrayOf(".pan", "application/x-pan"),
                arrayOf(".pbm", "image/x-portable-bitmap"),
                arrayOf(".pcx", "image/x-pcx"),
                arrayOf(".pda", "image/x-pda"),
                arrayOf(".pdb", "chemical/x-pdb"),
                arrayOf(".pdf", "application/pdf"),
                arrayOf(".pfr", "application/font-tdpfr"),
                arrayOf(".pfx", "application/x-pkcs12"),
                arrayOf(".pgm", "image/x-portable-graymap"),
                arrayOf(".pict", "image/x-pict"),
                arrayOf(".pko", "application/ynd.ms-pkipko"),
                arrayOf(".pm", "application/x-perl"),
                arrayOf(".pma", "application/x-perfmon"),
                arrayOf(".pmc", "application/x-perfmon"),
                arrayOf(".pmd", "application/x-pmd"),
                arrayOf(".pml", "application/x-perfmon"),
                arrayOf(".pmr", "application/x-perfmon"),
                arrayOf(".pmw", "application/x-perfmon"),
                arrayOf(".png", "image/png"),
                arrayOf(".pnm", "image/x-portable-anymap"),
                arrayOf(".pnz", "image/png"),
                arrayOf(".pot,", "application/vnd.ms-powerpoint"),
                arrayOf(".ppm", "image/x-portable-pixmap"),
                arrayOf(".pps", "application/vnd.ms-powerpoint"),
                arrayOf(".ppt", "application/vnd.ms-powerpoint"),
                arrayOf(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
                arrayOf(".pqf", "application/x-cprplayer"),
                arrayOf(".pqi", "application/cprplayer"),
                arrayOf(".prc", "application/x-prc"),
                arrayOf(".prf", "application/pics-rules"),
                arrayOf(".prop", "text/plain"),
                arrayOf(".proxy", "application/x-ns-proxy-autoconfig"),
                arrayOf(".ps", "application/postscript"),
                arrayOf(".ptlk", "application/listenup"),
                arrayOf(".pub", "application/x-mspublisher"),
                arrayOf(".pvx", "video/x-pv-pvx"),
                arrayOf(".qcp", "audio/vnd.qcelp"),
                arrayOf(".qt", "video/quicktime"),
                arrayOf(".qti", "image/x-quicktime"),
                arrayOf(".qtif", "image/x-quicktime"),
                arrayOf(".r3t", "text/vnd.rn-realtext3d"),
                arrayOf(".ra", "audio/x-pn-realaudio"),
                arrayOf(".ram", "audio/x-pn-realaudio"),
                arrayOf(".rar", "application/octet-stream"),
                arrayOf(".ras", "image/x-cmu-raster"),
                arrayOf(".rc", "text/plain"),
                arrayOf(".rdf", "application/rdf+xml"),
                arrayOf(".rf", "image/vnd.rn-realflash"),
                arrayOf(".rgb", "image/x-rgb"),
                arrayOf(".rlf", "application/x-richlink"),
                arrayOf(".rm", "audio/x-pn-realaudio"),
                arrayOf(".rmf", "audio/x-rmf"),
                arrayOf(".rmi", "audio/mid"),
                arrayOf(".rmm", "audio/x-pn-realaudio"),
                arrayOf(".rmvb", "audio/x-pn-realaudio"),
                arrayOf(".rnx", "application/vnd.rn-realplayer"),
                arrayOf(".roff", "application/x-troff"),
                arrayOf(".rp", "image/vnd.rn-realpix"),
                arrayOf(".rpm", "audio/x-pn-realaudio-plugin"),
                arrayOf(".rt", "text/vnd.rn-realtext"),
                arrayOf(".rte", "x-lml/x-gps"),
                arrayOf(".rtf", "application/rtf"),
                arrayOf(".rtg", "application/metastream"),
                arrayOf(".rtx", "text/richtext"),
                arrayOf(".rv", "video/vnd.rn-realvideo"),
                arrayOf(".rwc", "application/x-rogerwilco"),
                arrayOf(".s3m", "audio/x-mod"),
                arrayOf(".s3z", "audio/x-mod"),
                arrayOf(".sca", "application/x-supercard"),
                arrayOf(".scd", "application/x-msschedule"),
                arrayOf(".sct", "text/scriptlet"),
                arrayOf(".sdf", "application/e-score"),
                arrayOf(".sea", "application/x-stuffit"),
                arrayOf(".setpay", "application/set-payment-initiation"),
                arrayOf(".setreg", "application/set-registration-initiation"),
                arrayOf(".sgm", "text/x-sgml"),
                arrayOf(".sgml", "text/x-sgml"),
                arrayOf(".sh", "application/x-sh"),
                arrayOf(".shar", "application/x-shar"),
                arrayOf(".shtml", "magnus-internal/parsed-html"),
                arrayOf(".shw", "application/presentations"),
                arrayOf(".si6", "image/si6"),
                arrayOf(".si7", "image/vnd.stiwap.sis"),
                arrayOf(".si9", "image/vnd.lgtwap.sis"),
                arrayOf(".sis", "application/vnd.symbian.install"),
                arrayOf(".sit", "application/x-stuffit"),
                arrayOf(".skd", "application/x-Koan"),
                arrayOf(".skm", "application/x-Koan"),
                arrayOf(".skp", "application/x-Koan"),
                arrayOf(".skt", "application/x-Koan"),
                arrayOf(".slc", "application/x-salsa"),
                arrayOf(".smd", "audio/x-smd"),
                arrayOf(".smi", "application/smil"),
                arrayOf(".smil", "application/smil"),
                arrayOf(".smp", "application/studiom"),
                arrayOf(".smz", "audio/x-smd"),
                arrayOf(".snd", "audio/basic"),
                arrayOf(".spc", "application/x-pkcs7-certificates"),
                arrayOf(".spl", "application/futuresplash"),
                arrayOf(".spr", "application/x-sprite"),
                arrayOf(".sprite", "application/x-sprite"),
                arrayOf(".sdp", "application/sdp"),
                arrayOf(".spt", "application/x-spt"),
                arrayOf(".src", "application/x-wais-source"),
                arrayOf(".sst", "application/vnd.ms-pkicertstore"),
                arrayOf(".stk", "application/hyperstudio"),
                arrayOf(".stl", "application/vnd.ms-pkistl"),
                arrayOf(".stm", "text/html"),
                arrayOf(".svg", "image/svg+xml"),
                arrayOf(".sv4cpio", "application/x-sv4cpio"),
                arrayOf(".sv4crc", "application/x-sv4crc"),
                arrayOf(".svf", "image/vnd"),
                arrayOf(".svg", "image/svg+xml"),
                arrayOf(".svh", "image/svh"),
                arrayOf(".svr", "x-world/x-svr"),
                arrayOf(".swf", "application/x-shockwave-flash"),
                arrayOf(".swfl", "application/x-shockwave-flash"),
                arrayOf(".t", "application/x-troff"),
                arrayOf(".tad", "application/octet-stream"),
                arrayOf(".talk", "text/x-speech"),
                arrayOf(".tar", "application/x-tar"),
                arrayOf(".taz", "application/x-tar"),
                arrayOf(".tbp", "application/x-timbuktu"),
                arrayOf(".tbt", "application/x-timbuktu"),
                arrayOf(".tcl", "application/x-tcl"),
                arrayOf(".tex", "application/x-tex"),
                arrayOf(".texi", "application/x-texinfo"),
                arrayOf(".texinfo", "application/x-texinfo"),
                arrayOf(".tgz", "application/x-compressed"),
                arrayOf(".thm", "application/vnd.eri.thm"),
                arrayOf(".tif", "image/tiff"),
                arrayOf(".tiff", "image/tiff"),
                arrayOf(".tki", "application/x-tkined"),
                arrayOf(".tkined", "application/x-tkined"),
                arrayOf(".toc", "application/toc"),
                arrayOf(".toy", "image/toy"),
                arrayOf(".tr", "application/x-troff"),
                arrayOf(".trk", "x-lml/x-gps"),
                arrayOf(".trm", "application/x-msterminal"),
                arrayOf(".tsi", "audio/tsplayer"),
                arrayOf(".tsp", "application/dsptype"),
                arrayOf(".tsv", "text/tab-separated-values"),
                arrayOf(".ttf", "application/octet-stream"),
                arrayOf(".ttz", "application/t-time"),
                arrayOf(".txt", "text/plain"),
                arrayOf(".uls", "text/iuls"), arrayOf(".ult", "audio/x-mod"),
                arrayOf(".ustar", "application/x-ustar"),
                arrayOf(".uu", "application/x-uuencode"),
                arrayOf(".uue", "application/x-uuencode"),
                arrayOf(".vcd", "application/x-cdlink"),
                arrayOf(".vcf", "text/x-vcard"),
                arrayOf(".vdo", "video/vdo"),
                arrayOf(".vib", "audio/vib"),
                arrayOf(".viv", "video/vivo"),
                arrayOf(".vivo", "video/vivo"),
                arrayOf(".vmd", "application/vocaltec-media-desc"),
                arrayOf(".vmf", "application/vocaltec-media-file"),
                arrayOf(".vmi", "application/x-dreamcast-vms-info"),
                arrayOf(".vms", "application/x-dreamcast-vms"),
                arrayOf(".vox", "audio/voxware"),
                arrayOf(".vqe", "audio/x-twinvq-plugin"),
                arrayOf(".vqf", "audio/x-twinvq"),
                arrayOf(".vql", "audio/x-twinvq"),
                arrayOf(".vre", "x-world/x-vream"),
                arrayOf(".vrml", "x-world/x-vrml"),
                arrayOf(".vrt", "x-world/x-vrt"),
                arrayOf(".vrw", "x-world/x-vream"),
                arrayOf(".vts", "workbook/formulaone"),
                arrayOf(".wav", "audio/x-wav"),
                arrayOf(".wax", "audio/x-ms-wax"),
                arrayOf(".wbmp", "image/vnd.wap.wbmp"),
                arrayOf(".wcm", "application/vnd.ms-works"),
                arrayOf(".wdb", "application/vnd.ms-works"),
                arrayOf(".web", "application/vnd.xara"),
                arrayOf(".wi", "image/wavelet"),
                arrayOf(".wis", "application/x-InstallShield"),
                arrayOf(".wks", "application/vnd.ms-works"),
                arrayOf(".wm", "video/x-ms-wm"),
                arrayOf(".wma", "audio/x-ms-wma"),
                arrayOf(".wmd", "application/x-ms-wmd"),
                arrayOf(".wmf", "application/x-msmetafile"),
                arrayOf(".wml", "text/vnd.wap.wml"),
                arrayOf(".wmlc", "application/vnd.wap.wmlc"),
                arrayOf(".wmls", "text/vnd.wap.wmlscript"),
                arrayOf(".wmlsc", "application/vnd.wap.wmlscriptc"),
                arrayOf(".wmlscript", "text/vnd.wap.wmlscript"),
                arrayOf(".wmv", "audio/x-ms-wmv"),
                arrayOf(".wmx", "video/x-ms-wmx"),
                arrayOf(".wmz", "application/x-ms-wmz"),
                arrayOf(".wpng", "image/x-up-wpng"),
                arrayOf(".wps", "application/vnd.ms-works"),
                arrayOf(".wpt", "x-lml/x-gps"),
                arrayOf(".wri", "application/x-mswrite"),
                arrayOf(".wrl", "x-world/x-vrml"),
                arrayOf(".wrz", "x-world/x-vrml"),
                arrayOf(".ws", "text/vnd.wap.wmlscript"),
                arrayOf(".wsc", "application/vnd.wap.wmlscriptc"),
                arrayOf(".wv", "video/wavelet"),
                arrayOf(".wvx", "video/x-ms-wvx"),
                arrayOf(".wxl", "application/x-wxl"),
                arrayOf(".x-gzip", "application/x-gzip"),
                arrayOf(".xaf", "x-world/x-vrml"),
                arrayOf(".xar", "application/vnd.xara"),
                arrayOf(".xbm", "image/x-xbitmap"),
                arrayOf(".xdm", "application/x-xdma"),
                arrayOf(".xdma", "application/x-xdma"),
                arrayOf(".xdw", "application/vnd.fujixerox.docuworks"),
                arrayOf(".xht", "application/xhtml+xml"),
                arrayOf(".xhtm", "application/xhtml+xml"),
                arrayOf(".xhtml", "application/xhtml+xml"),
                arrayOf(".xla", "application/vnd.ms-excel"),
                arrayOf(".xlc", "application/vnd.ms-excel"),
                arrayOf(".xll", "application/x-excel"),
                arrayOf(".xlm", "application/vnd.ms-excel"),
                arrayOf(".xls", "application/vnd.ms-excel"),
                arrayOf(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
                arrayOf(".xlt", "application/vnd.ms-excel"),
                arrayOf(".xlw", "application/vnd.ms-excel"),
                arrayOf(".xm", "audio/x-mod"),
                arrayOf(".xml", "text/plain"),
                arrayOf(".xml", "application/xml"),
                arrayOf(".xmz", "audio/x-mod"),
                arrayOf(".xof", "x-world/x-vrml"),
                arrayOf(".xpi", "application/x-xpinstall"),
                arrayOf(".xpm", "image/x-xpixmap"),
                arrayOf(".xsit", "text/xml"),
                arrayOf(".xsl", "text/xml"),
                arrayOf(".xul", "text/xul"),
                arrayOf(".xwd", "image/x-xwindowdump"),
                arrayOf(".xyz", "chemical/x-pdb"),
                arrayOf(".yz1", "application/x-yz1"),
                arrayOf(".z", "application/x-compress"),
                arrayOf(".zac", "application/x-zaurus-zac"),
                arrayOf(".zip", "application/zip"),
                arrayOf(".json", "application/json"))

    }


}