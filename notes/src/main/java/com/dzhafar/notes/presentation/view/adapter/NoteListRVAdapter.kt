package com.dzhafar.notes.presentation.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dzhafar.notes.R
import com.dzhafar.notes.databinding.NoteListItemBinding
import com.dzhafar.notes.domain.models.NoteModel

class NoteListRVAdapter(
    private val clickItemCallback: (item: NoteModel) -> Unit,
    private val deleteItem: (item: NoteModel) -> Unit
) : RecyclerView.Adapter<NoteListRVAdapter.NoteListItemVH>() {
    companion object {
        val TAG = this::class.java.simpleName
    }

    var noteModelList: List<NoteModel> = listOf()

    fun setData(list: List<NoteModel>) {
        val diffUtil = NoteListDiffUtil(noteModelList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        noteModelList = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: NoteListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.note_list_item, parent, false
        )
        return NoteListItemVH(binding, clickItemCallback, deleteItem)
    }

    override fun getItemCount(): Int {
        return noteModelList.size
    }

    override fun onBindViewHolder(holder: NoteListItemVH, position: Int) {
        holder.bind(noteModelList[position])
    }

    override fun getItemId(position: Int): Long {
        return noteModelList[position].id!!
    }

    override fun onViewAttachedToWindow(holder: NoteListItemVH) {
        super.onViewAttachedToWindow(holder)
        holder.binding.motionContainer.progress = 0f
        holder.binding.motionContainer.setTransition(R.id.ending_set, R.id.deleted_set)
        val animation: Animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_left_to_right_anim)
        animation.interpolator = BounceInterpolator()
        holder.itemView.startAnimation(animation)
    }

    override fun onViewDetachedFromWindow(holder: NoteListItemVH) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    class NoteListItemVH(
        val binding: NoteListItemBinding,
        private val clickItemCallback: (item: NoteModel) -> Unit,
        private val deleteItemCallback: (item: NoteModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.note = item
            binding.motionContainer.setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {
                    TODO("Not yet implemented")
                }

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                    // TODO("Not yet implemented")
                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                    // TODO("Not yet implemented")
                }

                override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
                    Log.d(TAG, "${currentId == R.id.deleted_set}")
                    if (currentId == R.id.deleted_set) deleteItemCallback(item)
                }
            })
            binding.root.setOnClickListener {
                clickItemCallback(item)
            }
            binding.deleteBtn.setOnClickListener {
                binding.motionContainer.setTransition(R.id.ending_set, R.id.deleted_set)
                binding.motionContainer.transitionToEnd()
            }
        }
    }

    class NoteListDiffUtil(
        private val oldList: List<NoteModel>,
        private val newList: List<NoteModel>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].date == newList[newItemPosition].date &&
                oldList[oldItemPosition].text == newList[newItemPosition].text &&
                oldList[oldItemPosition].title == newList[newItemPosition].title
        }
    }
}
