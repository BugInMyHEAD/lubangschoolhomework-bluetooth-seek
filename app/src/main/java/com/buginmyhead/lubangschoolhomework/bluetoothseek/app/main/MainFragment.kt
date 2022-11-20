package com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.buginmyhead.lubangschoolhomework.bluetoothseek.app.FromResources
import com.buginmyhead.lubangschoolhomework.bluetoothseek.app.databinding.FragmentMainBinding
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekFailure
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val fromResources by lazy { FromResources(resources) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.bluetoothSeekLiveData.observe(viewLifecycleOwner) {
            it.showState(object : ViewState.Presenter<Boolean, Unit, BluetoothSeekFailure> {

                override fun onSuccess(data: Boolean) {
                    binding.message.text = data.toString()
                }

                override fun onLoading(data: Unit) {
                }

                override fun onFailure(data: BluetoothSeekFailure) {
                    binding.message.text = data.toString()
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