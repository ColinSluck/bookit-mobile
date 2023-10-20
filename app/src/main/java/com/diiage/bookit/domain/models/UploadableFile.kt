package com.diiage.bookit.domain.models

import java.io.InputStream

data class UploadableFile(val name: String, val inputStream: InputStream? = null, val byteArray: ByteArray? = null, val mimeType: String? = null)
