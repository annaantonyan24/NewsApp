package com.example.diplomayin.fragments.addNews

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.Navigation
import coil.clear
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.data.model.model.room.MyNewsDataModel
import com.example.data.util.Constants.Companion.DATE_PATTERN
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.R
import com.example.diplomayin.databinding.DialogCustomimageSelectionBinding
import com.example.diplomayin.databinding.FragmentAddNewsBinding
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class AddNewsFragment : FragmentBaseMVVM<FragmentAddNewsBinding>() {

    private val viewModel: AddNewsViewModel by viewModel()
    override val binding: FragmentAddNewsBinding by viewBinding()
    private var mImagePath: String = ""
    private var data: MyNewsDataModel? = null

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    val sdf = SimpleDateFormat(DATE_PATTERN)

    @RequiresApi(Build.VERSION_CODES.N)
    val currentDate: String = sdf.format(Date())

    override fun onView() {
        val bundle = this.arguments
        data = bundle?.getParcelable(NewsConstants.NEWS_BUNDLE)

        if (data != null) {
            with(binding) {
                etTitle.setText(data?.title)
                etCategory.setText(data?.category)
                etDescription.setText(data?.description)
                etContent.setText(data?.content)
                mImagePath = data?.image.toString()
                ivAddImageCenter.visibility = View.GONE
                ivEditImage.visibility = View.VISIBLE
                btnAddNews.text = resources.getString(R.string.btn_update_news)
                Glide.with(this@AddNewsFragment)
                    .load(data?.image)
                    .centerCrop()
                    .into(binding.ivNewsImage)
            }
        }

        binding.flSelectImage.setOnClickListener {
            customImageSelectionDialog()
        }

        binding.btnAddNews.setOnClickListener {
            myNewsData()
        }
    }


    private fun myNewsData() {
        val title = binding.etTitle.text.toString().trim { it <= ' ' }
        val description = binding.etDescription.text.toString().trim { it <= ' ' }
        val category = binding.etCategory.text.toString().trim { it <= ' ' }
        val content = binding.etContent.text.toString().trim { it <= ' ' }

        when {
            TextUtils.isEmpty(mImagePath) -> {
                Toast.makeText(
                    context,
                    resources.getString(R.string.err_msg_select_img),
                    Toast.LENGTH_SHORT
                ).show()

            }

            TextUtils.isEmpty(title) -> {
                Toast.makeText(
                    context,
                    resources.getString(R.string.err_msg_enter_news_title),
                    Toast.LENGTH_SHORT
                ).show()
            }

            TextUtils.isEmpty(description) -> {
                Toast.makeText(
                    context,
                    resources.getString(R.string.err_msg_enter_news_description),
                    Toast.LENGTH_SHORT
                ).show()
            }

            TextUtils.isEmpty(category) -> {
                Toast.makeText(
                    context,
                    resources.getString(R.string.err_msg_select_news_category),
                    Toast.LENGTH_SHORT
                ).show()
            }

            TextUtils.isEmpty(content) -> {
                Toast.makeText(
                    context,
                    resources.getString(R.string.err_msg_enter_news_content),
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {
                val myNewsData = MyNewsDataModel(
                    title = title,
                    description = description,
                    category = category,
                    image = mImagePath,
                    publishedAt = currentDate,
                    content = content
                )

                if (data == null) {
                    viewModel.insertMyNews(myNewsData)
                    Toast.makeText(context, "You successfully created yor News!", Toast.LENGTH_SHORT)
                        .show()
                    with(binding) {
                        etTitle.text.clear()
                        etCategory.text.clear()
                        etContent.text.clear()
                        etDescription.text.clear()
                        ivNewsImage.setImageResource(0)
                        ivAddImageCenter.visibility = View.VISIBLE
                        ivEditImage.visibility = View.GONE
                    }
                } else {
                    viewModel.updateMyNews(myNewsData)
                    Toast.makeText(context, "You successfully updated yor News!", Toast.LENGTH_SHORT)
                        .show()
                }

            }

        }
    }

    private fun customImageSelectionDialog() {

        val dialog = Dialog(requireContext())
        val binding: DialogCustomimageSelectionBinding =
            DialogCustomimageSelectionBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.tvCamera.setOnClickListener {
            Dexter.withContext(context).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (report.areAllPermissionsGranted()) {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, CAMERA)
                        }
                    }

                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    showRationalDialogsPermissions()
                }

            }).onSameThread().check()

            dialog.dismiss()
        }

        binding.tvGallery.setOnClickListener {
            Dexter.withContext(context).withPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    val galleryIntent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    startActivityForResult(galleryIntent, GALLERY)
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    Toast.makeText(
                        context,
                        "You have denied the gallery permission",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    showRationalDialogsPermissions()
                }

            }).onSameThread().check()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showRationalDialogsPermissions() {
        AlertDialog.Builder(context)
            .setMessage(this.resources.getString(R.string.permission_not_available_message))
            .setPositiveButton("GO TO SETTINGS") { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", "packageName", null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA) {
                data?.extras?.let {
                    val thumbNail: Bitmap = data.extras!!.get("data") as Bitmap

                    Glide.with(this)
                        .load(thumbNail)
                        .centerCrop()
                        .into(binding.ivNewsImage)

                    mImagePath = saveImageToInternalStorage(thumbNail)

                    binding.ivAddImageCenter.visibility = View.GONE
                    binding.ivEditImage.visibility = View.VISIBLE
                }
            }

            if (requestCode == GALLERY) {
                data?.let {
                    val selectedPhotoUri = data.data

                    Glide.with(this)
                        .load(selectedPhotoUri)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                resource?.let {
                                    val bitmap: Bitmap = resource.toBitmap()
                                    mImagePath = saveImageToInternalStorage(bitmap)
                                }
                                return false
                            }

                        })
                        .into(binding.ivNewsImage)

                    binding.ivAddImageCenter.visibility = View.GONE
                    binding.ivEditImage.visibility = View.VISIBLE
                }
            }

        }

    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): String {

        val wrapper = ContextWrapper(requireContext())
        var file = wrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file.absolutePath
    }

    companion object {
        private const val CAMERA = 1
        private const val GALLERY = 2
        private const val IMAGE_DIRECTORY = "NewsImages"
    }

}