package com.gmail.bakareritesh1729.spamalert.TabLayout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.gmail.bakareritesh1729.spamalert.R
import com.gmail.bakareritesh1729.spamalert.databinding.FragmentPhoneBinding
import com.gmail.bakareritesh1729.spamalert.helpers.TextClassificationClient
import org.tensorflow.lite.support.label.Category
import java.util.*


class PhoneFragment : Fragment() {

    private lateinit var binding: FragmentPhoneBinding

    private val allowPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            it.let {
                if (it) {
                    Toast.makeText(
                        context?.applicationContext,
                        "Permission Granted ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    private val speechRecognizer: SpeechRecognizer by lazy {
        SpeechRecognizer.createSpeechRecognizer(context?.applicationContext)
    }

    private lateinit var client: TextClassificationClient

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneBinding.inflate(layoutInflater, container, false)

        client = TextClassificationClient(context?.applicationContext)
        client.load()

        binding.btnListen.setOnTouchListener { _, motionEvent ->

            when (motionEvent.action) {

                MotionEvent.ACTION_UP -> {
                    speechRecognizer.stopListening()
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_DOWN -> {
                    getPermissionOver(context?.applicationContext!!) {
                        startListening()
                    }
                    return@setOnTouchListener true
                }
                else -> {
                    return@setOnTouchListener true
                }
            }

        }





        return binding.root
    }

    private fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
            }

            override fun onBeginningOfSpeech() {
                binding.edVoiceInput.setText("Listening")
            }

            override fun onRmsChanged(rmsdB: Float) {
            }

            override fun onBufferReceived(buffer: ByteArray?) {
            }

            override fun onEndOfSpeech() {
            }

            override fun onError(error: Int) {
            }

            override fun onResults(bundel: Bundle?) {
                bundel?.let {
                    val result = it.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    binding.edVoiceInput.setText(result?.get(0))
                    checkSpamCall(result?.get(0).toString())
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {
            }

            override fun onEvent(eventType: Int, params: Bundle?) {
            }

        })

        speechRecognizer.startListening(intent)

    }

    private fun checkSpamCall(text: String) {
        val result: List<Category> = client.classify(text)
        val score = result[1].score
        if (score > 0.7) {
            binding.tvSpam.text = "This Call is spam with score $score "
        } else {
            binding.tvSpam.text = "This Call is Not Spam score: $score "
        }
    }

    private fun getPermissionOver(context: Context, call: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ActivityCompat.checkSelfPermission(
                    context, android.Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                call.invoke()
            } else {
                allowPermission.launch(android.Manifest.permission.RECORD_AUDIO)
            }
        }

    }


}