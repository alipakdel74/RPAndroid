package ir.rahkarpouya.rpa.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import ir.rahkarpouya.rpa.R
import ir.rahkarpouya.rpa.RPA.Message.Error
import ir.rahkarpouya.rpa.RPA.Message.Info
import ir.rahkarpouya.rpa.RPA.Message.Stop
import ir.rahkarpouya.rpa.RPA.Message.Success

class QuestionDialog(
    private val mContext: Context,
    private val desc: String,
    private val txtBtnOk: String = "تأیید",
    private val txtBtnNo: String = "انصراف",
    private val messageType: Int = 0,
    private val listenerOk: View.OnClickListener? = null,
    private val listenerNo: View.OnClickListener? = null
) : Dialog(mContext) {

    private lateinit var dqIvTitle: AppCompatImageView
    private lateinit var dqTxtDesc: AppCompatTextView
    private lateinit var dqTxtNo: AppCompatTextView
    private lateinit var dqTxtOk: AppCompatTextView
    private lateinit var dqCardBtnNo: CardView
    private lateinit var dqCardBtnOk: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_question)
        setCancelable(false)
//        window!!.attributes.windowAnimations = R.style.PauseDialog
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
        initView()

        dqTxtDesc.text = desc

        if (txtBtnOk != "")
            dqTxtOk.text = txtBtnOk

        if (txtBtnNo != "")
            dqTxtNo.text = txtBtnNo

        dqCardBtnOk.setOnClickListener {
            listenerOk?.onClick(dqCardBtnOk)
            dismiss()
        }

        dqCardBtnNo.setOnClickListener {
            listenerNo?.onClick(dqCardBtnNo)
            dismiss()
        }

        when (messageType) {
            Success -> dqIvTitle.setBackgroundColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.colorOk
                )
            )
            Info -> dqIvTitle.setBackgroundColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.colorInfo
                )
            )
            Error -> dqIvTitle.setBackgroundColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.colorError
                )
            )
            Stop -> dqIvTitle.setBackgroundColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.colorStop
                )
            )
            else ->
                dqIvTitle.rootView.setBackgroundColor(ContextCompat.getColor(mContext, messageType))
        }

    }

    private fun initView() {
        dqIvTitle = findViewById(R.id.dq_iv_title)
        dqTxtDesc = findViewById(R.id.dq_txt_desc)
        dqTxtNo = findViewById(R.id.dq_txt_no)
        dqTxtOk = findViewById(R.id.dq_txt_ok)
        dqCardBtnNo = findViewById(R.id.dq_card_btn_no)
        dqCardBtnOk = findViewById(R.id.dq_card_btn_ok)
    }

    override fun onBackPressed() {
        if (messageType != 3)
            if (isShowing)
                dismiss()
            else
                super.onBackPressed()
    }
}
