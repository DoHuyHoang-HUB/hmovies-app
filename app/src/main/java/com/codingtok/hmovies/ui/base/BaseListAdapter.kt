package com.codingtok.hmovies.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.codingtok.hmovies.BR
import java.util.concurrent.Executors

private interface BaseRecyclerAdapter<T : Any, ViewBinding : ViewDataBinding> {
    /**
     * get layout res base on view type
     */
    fun getLayoutRes(viewType: Int): Int

    /**
     * bind first time
     * use for set item on click listener, something only set one time
     */
    fun bindFirstTime(binding: ViewBinding) {}

    /**
     * bind view
     */
    fun bindView(binding: ViewBinding, item: T, position: Int) {}

}

/**
 * base recycler view adapter
 */
abstract class BaseListAdapter<T : Any, ViewBinding : ViewDataBinding>(
    callBack: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder<ViewBinding>>(
    AsyncDifferConfig.Builder<T>(callBack)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
), BaseRecyclerAdapter<T, ViewBinding> {

    /**
     * override this with new list to pass check "if (newList == mList)" in AsyncListDiffer
     */
    override fun submitList(list: List<T>?) {
        super.submitList(ArrayList<T>(list ?: listOf()))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding> {
        return BaseViewHolder(DataBindingUtil.inflate<ViewBinding>(
            LayoutInflater.from(parent.context),
            getLayoutRes(viewType),
            parent,
            false
        ).apply {
            bindFirstTime(this)
        })
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        val item: T? = getItem(position)
        holder.binding.setVariable(BR.item, item)
        if (item != null) {
            bindView(holder.binding, item, position)
        }
        holder.binding.executePendingBindings()
    }
}


open class BaseViewHolder<ViewBinding : ViewDataBinding>(
    val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root)



