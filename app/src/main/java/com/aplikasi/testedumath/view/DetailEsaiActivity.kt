package com.aplikasi.testedumath.view

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.testedumath.R
import com.aplikasi.testedumath.databinding.ActivityDetailEsaiBinding
import com.aplikasi.testedumath.model.Data
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import io.github.kexanie.library.MathView

class DetailEsaiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailEsaiBinding
    private lateinit var data: Data

    private var mBehavior: BottomSheetBehavior<*>? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    private val bottom_sheet: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEsaiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
        initListener()
    }

    private fun initListener() {
        binding.btnJawab.setOnClickListener {
            if (binding.edtJawaban.text.toString() == ""){

            }else{
                if (binding.edtJawaban.text.toString() == data.shortAnswer!![0].shortAnswerText){
                    binding.edtJawaban.background = resources.getDrawable(
                        R.drawable.btn_rounded_green,
                        null
                    )
                    binding.edtJawaban.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_baseline_check_24,
                        0
                    )
                }else{
                    binding.edtJawaban.background = resources.getDrawable(
                        R.drawable.btn_rounded_red,
                        null
                    )
                    binding.edtJawaban.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_baseline_close_24,
                        0
                    )
                }
            }
        }

        binding.btnPembahasan.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun initView() {
        mBehavior = BottomSheetBehavior.from(binding.bottomSheet)

        data = this.intent.getSerializableExtra("DATA") as Data

        binding.txtSoal.text = data.text
    }

    private fun showBottomSheetDialog() {
        if (mBehavior!!.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        val view: View = layoutInflater.inflate(R.layout.bottom_sheet, null)
        (view.findViewById<View>(R.id.txtPembahasan) as MathView).setText(data.discussion)
        view.findViewById<View>(R.id.imgClose).setOnClickListener { mBottomSheetDialog!!.dismiss() }

        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog!!.setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog!!.getWindow()!!
                .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        mBottomSheetDialog!!.show()
        mBottomSheetDialog!!.setOnDismissListener(DialogInterface.OnDismissListener {
            mBottomSheetDialog = null
        })
    }
}