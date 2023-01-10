package com.phrasenote.core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.util.*

object DataMapper {

    val monthsList: List<String> = listOf(
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Octubre",
        "Noviembre",
        "Diciembre"
    )

    fun getDate(): String {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        var monthTemp = monthsList[month]

        return "Creado el $day de  $monthTemp del $year"
    }

    fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.getUrlEncoder().encodeToString(b)
    }

    fun decodeImage(uri: String): Bitmap? {
        var uriString = Base64.getUrlDecoder().decode(uri)
        val bitmap = BitmapFactory.decodeByteArray(uriString, 0, uriString.size)

        return bitmap
    }

}