package com.codingtok.hmovies.ui.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.codingtok.common.MultipleStatusView
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber

abstract class BaseActivity: AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        initView()
        initData()

        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    /**
     * click listener when erro
     */
    protected open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }

    abstract fun layoutId(): Int

    /**
     * Init view
     */
    abstract fun initView()

    /**
     * Init data
     */
    abstract fun initData()

    /**
     * retry load data
     */
    abstract fun lazyLoad()

    /**
     * open key board
     *
     * @param editText
     * @param context
     */
    fun openKeyBoard(editText: EditText, context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.apply {
            showSoftInput(editText, InputMethodManager.RESULT_SHOWN)
            toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }

    /**
     * close key board
     *
     * @param editText
     * @param context
     */
    fun closeKeyBoard(editText: EditText, context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    /**
     * Ghi ???? c??c ph????ng th???c onRequestPermisstionResult() c???a activity ho???c
     * fragment m?? m??nh mu???n g???i quy???n g??i EasyPermisstion
     *
     * @param requestCode id c???a y??u c???u quy???n
     * @param permissions xin ph??p quy???n
     * @param grantResults k???t qu???
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * G???i khi quy???n ???????c ??p d???ng th??nh c??ng
     *
     * @param requestCode id c???a y??u c???u quy???n
     * @param perms
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Timber.tag("EasyPermisstion").i("xin quy???n th??nh c??ng $perms")
    }

    /**
     * ???????c g???i khi y??u c???u quy???n kh??ng th??nh c??ng
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        val sb = StringBuilder()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }

        sb.replace(sb.length - 2, sb.length, "")

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "Quy???n b??? t??? ch???i" + sb + "v?? kh??ng c??n h???i n???a", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                .setRationale("Ch???c n??ng n??y y??u c???u" + sb + "quy???n, n???u kh??ng n?? s??? kh??ng s??? d???ng b??nh th?????ng")
                .setPositiveButton("?????ng ??")
                .setNegativeButton("H???y")
                .build()
                .show()
        }
    }
}