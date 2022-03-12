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
     * Ghi đè các phương thức onRequestPermisstionResult() của activity hoặc
     * fragment mà mình muốn gọi quyền gói EasyPermisstion
     *
     * @param requestCode id của yêu cầu quyền
     * @param permissions xin phép quyền
     * @param grantResults kết quả
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
     * Gọi khi quyền được áp dụng thành công
     *
     * @param requestCode id của yêu cầu quyền
     * @param perms
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Timber.tag("EasyPermisstion").i("xin quyền thành công $perms")
    }

    /**
     * Được gọi khi yêu cầu quyền không thành công
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        val sb = StringBuilder()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }

        sb.replace(sb.length - 2, sb.length, "")

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "Quyền bị từ chối" + sb + "và không còn hỏi nữa", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                .setRationale("Chức năng này yêu cầu" + sb + "quyền, nếu không nó sẽ không sử dụng bình thường")
                .setPositiveButton("Đồng ý")
                .setNegativeButton("Hủy")
                .build()
                .show()
        }
    }
}