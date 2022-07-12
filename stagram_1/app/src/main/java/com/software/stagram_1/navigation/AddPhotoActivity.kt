package com.software.stagram_1.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.storage.FirebaseStorage
import com.software.stagram_1.R
import kotlinx.android.synthetic.main.activity_add_photo.*
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : AppCompatActivity() {
    var PICK_IMAGE_FROM_ALBYM = 0
    var storage : FirebaseStorage? = null
    var photoUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_photo)

        //스토리지 초기화화
       storage = FirebaseStorage.getInstance()

        //엑티비티가 실행되면 앨범오픈
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBYM)

        //사진추가 이벤트
        add_photo_btn_upload.setOnClickListener {
            contentUpload()
        }

    }

    //선택한 이미지를 받기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==PICK_IMAGE_FROM_ALBYM){
            if(resultCode == Activity.RESULT_OK){
                //사진을 선택시 이미지 경로
                photoUri = data?.data
                addphoto_image.setImageURI(photoUri)
            }else{
                //취소버튼 선택 시 작동
                finish()
            }
        }
    }

    fun contentUpload(){
        //파일명 설정
        var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imageFileName = "IMAGE_" + timestamp + ".png"

        var storageRef = storage?.reference?.child("images")?.child(imageFileName)

        //파일 업로드
        storageRef?.putFile(photoUri!!)?.addOnCompleteListener{
            Toast.makeText(this,getString(R.string.upload_success),Toast.LENGTH_LONG).show()
        }
    }

}