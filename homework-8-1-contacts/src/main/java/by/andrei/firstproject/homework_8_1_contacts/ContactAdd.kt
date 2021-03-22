package by.andrei.firstproject.homework_8_1_contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import by.andrei.firstproject.homework_8_1_contacts.const.Constants
import by.andrei.firstproject.homework_8_1_contacts.const.Constants.CONTACT_LIST_FRAGMENT
import by.andrei.firstproject.homework_8_1_contacts.data.Contact
import by.andrei.firstproject.homework_8_1_contacts.databinding.FragmentAddBinding

class ContactAdd : Fragment(R.layout.fragment_add) {
    private lateinit var setTextName: EditText
    private lateinit var setTextPhoneOrEmail: EditText
    private lateinit var buttonAddContactInfo: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioPhone: RadioButton
    private lateinit var radioEmail: RadioButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTextName = view.findViewById(R.id.addTextForName)
        setTextPhoneOrEmail = view.findViewById(R.id.addTextForPhoneNumber)
        buttonAddContactInfo = view.findViewById(R.id.buttonAdd)
        radioGroup = view.findViewById(R.id.radioGroupAdd)
        radioPhone = view.findViewById(R.id.phoneCheck)
        radioEmail = view.findViewById(R.id.emailCheck)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            view.findViewById<RadioButton>(checkedId)?.apply {
                setTextPhoneOrEmail.hint = text
            }
        }

        buttonAddContactInfo.setOnClickListener {
            val getName = setTextName.text.toString()
            val getPhone = setTextPhoneOrEmail.text.toString()
            val getImage = if(radioPhone.isChecked) {
                R.drawable.ic_baseline_local_phone_24
            } else {
                R.drawable.ic_baseline_local_post_office_24
            }
            val bundle = Bundle()
            bundle.putParcelable("KEY", Contact(getImage, getName, getPhone))

            (activity as OnChangeFragmentListener).onFragmentChange(CONTACT_LIST_FRAGMENT, bundle)
        }
    }
}