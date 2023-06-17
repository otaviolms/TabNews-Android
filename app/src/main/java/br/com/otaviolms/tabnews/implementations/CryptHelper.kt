package br.com.otaviolms.tabnews.implementations

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import okio.Utf8
import java.nio.charset.Charset
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.Cipher.DECRYPT_MODE
import javax.crypto.Cipher.ENCRYPT_MODE
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CryptHelper {

    private const val ALGORITHM = "AES/GCM/NoPadding"
    private const val T_LEN = 128
    private val KEY = "TABNEWSAPPKOTLIN".toByteArray()

    // Função para criptografar uma string usando AES
    fun encrypt(valor: String): Pair<String, String> {
        val cipher = Cipher.getInstance(ALGORITHM)
        val sr = SecureRandom()
        var newIv = sr.generateSeed(T_LEN)
        var valorCriptografado = valor
        try {
            newIv = newIv.copyOfRange(0, 12)
            val s = GCMParameterSpec(T_LEN, newIv)
            val secretKey = SecretKeySpec(KEY, ALGORITHM)
            cipher.init(ENCRYPT_MODE, secretKey, s)
            cipher.updateAAD(s.iv)
            val valorCriptografadoBytes = cipher.doFinal(valor.toByteArray(Charsets.UTF_8))
            valorCriptografado = byteArrayToString(valorCriptografadoBytes)
        } catch (e: Exception) {
            Log.e("LogTabNews", "Falha ao criptografar valor")
            Log.e("LogTabNews", e.message.toString())
            Log.e("LogTabNews", e.stackTraceToString())
        }
        return valorCriptografado to byteArrayToString(newIv)
    }

    // Função para descriptografar uma string usando AES
    fun decrypt(valorCriptografado: String, iv: String): String {
        var valorDescriptografado = ""
        val valorCriptografadoBytes = stringToByteArray(valorCriptografado)
        val valorIV = stringToByteArray(iv)

        try {
            val cipher = Cipher.getInstance(ALGORITHM)
            val s = GCMParameterSpec(T_LEN, valorIV)
            val secretKey = SecretKeySpec(KEY, ALGORITHM)
            cipher.init(DECRYPT_MODE, secretKey, s)
            cipher.updateAAD(s.iv)
            val valorDescriptografadoBytes = cipher.doFinal(valorCriptografadoBytes)
            valorDescriptografado = String(valorDescriptografadoBytes, Charsets.UTF_8)
        } catch (e: Exception) {
            Log.e("LogTabNews", "Falha ao descriptografar valor")
            Log.e("LogTabNews", e.message.toString())
            Log.e("LogTabNews", e.stackTraceToString())
        }
        return valorDescriptografado
    }

    private fun byteArrayToString(byteArray: ByteArray): String {
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    private fun stringToByteArray(string: String): ByteArray {
        return Base64.decode(string, Base64.DEFAULT)
    }

}
