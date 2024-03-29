package com.target.targetcasestudy.payment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.jakewharton.rxbinding.widget.RxTextView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.payment.manager.Validators
import dagger.android.support.AndroidSupportInjection
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : DialogFragment() {

    private lateinit var submitButton: Button
    private lateinit var creditCardInput: EditText

    @Inject
    lateinit var validators: Validators

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)

        val root = inflater.inflate(R.layout.dialog_payment, container, false)

        submitButton = root.findViewById(R.id.submit)
        creditCardInput = root.findViewById(R.id.card_number)
        val cancelButton: Button = root.findViewById(R.id.cancel)

        cancelButton.setOnClickListener { dismiss() }
        submitButton.setOnClickListener { dismiss() }

        textViewListener()

        return root
    }

    private fun textViewListener() {
        RxTextView.textChanges(creditCardInput)
            .debounce(200, TimeUnit.MILLISECONDS)
            .map { validators.validateCreditCard(it.toString()) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isValid ->
                submitButton.isEnabled = isValid
            }
    }

}