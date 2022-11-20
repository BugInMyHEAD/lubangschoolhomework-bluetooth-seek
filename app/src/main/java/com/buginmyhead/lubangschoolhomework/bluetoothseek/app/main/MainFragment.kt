package com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.buginmyhead.lubangschoolhomework.bluetoothseek.app.databinding.FragmentMainBinding
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.Logger
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekFailure
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    internal lateinit var logger: Logger

    @Inject
    internal lateinit var bluetoothSeekUseCase: BluetoothSeekUseCase

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            bluetoothSeekUseCase.start()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.bluetoothSeekLiveData.observe(viewLifecycleOwner) { viewState ->
            viewState.present(object : ViewState.Presenter<Boolean, Unit, BluetoothSeekFailure> {

                override fun onSuccess(data: Boolean) {
                }

                override fun onLoading(data: Unit) {
                }

                override fun onFailure(data: BluetoothSeekFailure) {
                    logger.d("4f1b305c-eb97-41ab-9d10-49813ec929e1", data)
                    when (data) {
                        BluetoothSeekFailure.NO_PERMISSION -> {
                            val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) Manifest.permission.BLUETOOTH_CONNECT
                            else Manifest.permission.BLUETOOTH
                            requestPermissionLauncher.launch(permission)
                        }
                        else -> {}
                    }
                }

            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        fun newInstance() = MainFragment()

    }

}