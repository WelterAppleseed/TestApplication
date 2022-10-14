package com.example.testapplication.presentation.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<V : ViewBinding>(
    private val binder: (LayoutInflater, ViewGroup?, Boolean) -> V
) : Fragment() {

    private var contentBinding: V? = null
    protected open var windowSoftInput = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
    protected val binding: V
        get() = requireNotNull(contentBinding) {
            "Binding is only valid between onCreateView and onDestroyView"
        }

    override fun onStart() {
        super.onStart()
        liftViewsWithKeyboard(requireActivity().window)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentBinding = binder.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        contentBinding = null
        super.onDestroyView()
    }
    private fun liftViewsWithKeyboard(window: Window) {
        window.setSoftInputMode(windowSoftInput)
    }
}