package com.nm.as_024_camera

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.core.content.FileProvider
import java.io.File
import java.lang.Integer.min

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var ivPhoto: ImageView

    private var localPath = ""
    private var finalPath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        ivPhoto = findViewById(R.id.ivPhoto)

        val path = getExternalFilesDirs(null)
        localPath = "${path[0].path}/DBase"
    }

    private fun iniActions() {
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            chamarCapturaDeImagem()
        }
    }

    private fun chamarCapturaDeImagem() {
        if (hasStoragePermission(context)) {
            criarDiretorio(localPath)

            finalPath = "$localPath/foto_${System.currentTimeMillis()}.jpg"

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(
                    MediaStore.EXTRA_OUTPUT, // quero informar o nome e o local da gravacao
                    FileProvider.getUriForFile(
                            context,
                            "${BuildConfig.APPLICATION_ID}.provider",
                            File(finalPath)
                    )
            )

            startActivityForResult(intent, PROCESSO_TIRAR_FOTO)
        } else {
            requestStoragePermission(this)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            PROCESSO_TIRAR_FOTO -> {
                setPic(finalPath, ivPhoto)
            }
            else -> {

            }
        }
    }

    private fun setPic(finalPath: String, ivPhoto: ImageView) {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true // (NAO)obter a imagem (LER) informacoes da imagem

        BitmapFactory.decodeFile(finalPath, options) // ler as inforcoes da imagem e armazena dentro do proprio options

        // referencia do meu ImageView
        val targetW = ivPhoto.width
        val targetH = ivPhoto.height

        // referencia a imagem tirada
        val photoW = options.outWidth
        val photoH = options.outHeight

        val escalaDeReducao = min(photoW / targetW, photoH / targetH)

        options.inJustDecodeBounds = false
        options.inSampleSize = escalaDeReducao

        ivPhoto.setImageBitmap(
            BitmapFactory.decodeFile(finalPath, options)
        )
    }

    companion object {
        const val PROCESSO_TIRAR_FOTO = 100
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}