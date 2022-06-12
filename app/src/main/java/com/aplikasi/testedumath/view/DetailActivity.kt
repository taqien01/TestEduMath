package com.aplikasi.testedumath.view

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.testedumath.R
import com.aplikasi.testedumath.databinding.ActivityDetailBinding
import com.aplikasi.testedumath.model.Data
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import io.github.kexanie.library.MathView

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var data: Data

    private var jawaban: String = ""
    private var jawabanId: Int = 0
    private lateinit var lockView: View

    private var mBehavior: BottomSheetBehavior<*>? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    private val bottom_sheet: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()

        initListener()

        lockView = binding.txtA
    }

    private fun initListener() {
        binding.txtA.setOnClickListener {
            setNormal()
            setChoice(it)

            jawaban = data.selection!![0].selectionText!!
            jawabanId = data.selection!![0].idSelection!!

            lockView = it
        }

        binding.txtB.setOnClickListener {
            setNormal()
            setChoice(it)

            jawaban = data.selection!![1].selectionText!!
            jawabanId = data.selection!![1].idSelection!!
            lockView = it
        }

        binding.txtC.setOnClickListener {
            setNormal()
            setChoice(it)

            jawaban = data.selection!![2].selectionText!!
            jawabanId = data.selection!![2].idSelection!!
            lockView = it
        }

        binding.txtD.setOnClickListener {
            setNormal()
            setChoice(it)

            jawaban = data.selection!![3].selectionText!!
            jawabanId = data.selection!![3].idSelection!!
            lockView = it
        }

        binding.txtE.setOnClickListener {
            setNormal()
            setChoice(it)

            jawaban = data.selection!![4].selectionText!!
            jawabanId = data.selection!![4].idSelection!!
            lockView = it
        }

        binding.btnJawab.setOnClickListener {
            if (data.selectionAnswer!![0].selectionId == jawabanId){
                setGreen(lockView)
            }else{
                setRed(lockView)
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

        binding.txtA.text = "A. "+data.selection!![0].selectionText
        binding.txtB.text = "B. "+data.selection!![1].selectionText
        binding.txtC.text = "C. "+data.selection!![2].selectionText
        binding.txtD.text = "D. "+data.selection!![3].selectionText
        binding.txtE.text = "E. "+data.selection!![4].selectionText
    }

    fun setNormal(){
        binding.txtA.background = resources.getDrawable(R.drawable.btn_rounded_grey_outline, null)
        binding.txtB.background = resources.getDrawable(R.drawable.btn_rounded_grey_outline, null)
        binding.txtC.background = resources.getDrawable(R.drawable.btn_rounded_grey_outline, null)
        binding.txtD.background = resources.getDrawable(R.drawable.btn_rounded_grey_outline, null)
        binding.txtE.background = resources.getDrawable(R.drawable.btn_rounded_grey_outline, null)
//        btn.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)
    }

    fun setChoice(btn: View){
        btn.background = resources.getDrawable(R.drawable.btn_rounded_blue, null)
//        btn.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)
    }

    fun setRed(btn: View){
        btn.background = resources.getDrawable(R.drawable.btn_rounded_red, null)
//        btn.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_close_24,0)
    }

    fun setGreen(btn: View){
        btn.background = resources.getDrawable(R.drawable.btn_rounded_green, null)
//        btn.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_check_24,0)
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