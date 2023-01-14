package com.subhash1e.masteringpdf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class pdfRowAdapter(val mList: List<SubjectDataClass>): RecyclerView.Adapter<pdfRowAdapter.VH>() {
    class VH(ItemView:View):RecyclerView.ViewHolder(ItemView){
    }

    // initialize view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pdf_row_for_rv,parent,false)
        val vh = VH(view)
        return vh
    }
// binds data to view in rv

    override fun onBindViewHolder(holder: VH, position: Int) {
      val item = mList[position]
        holder.apply {
            val tvSubjectName = itemView.findViewById<TextView>(R.id.tvSubjectName)
            val tvSubjectMarks = itemView.findViewById<TextView>(R.id.tvSubjectMarks)
            tvSubjectName.text = item.sName
            tvSubjectMarks.text = item.sMarks.toString()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}