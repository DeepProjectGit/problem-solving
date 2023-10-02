package com.example.precticaltask

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.precticaltask.databinding.ActivityMainBinding
import com.example.precticaltask.databinding.ActivitySecoundBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding:ActivitySecoundBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countGet = intent.getIntExtra("col",0) * intent.getIntExtra("row",0)

        val colCount =  intent.getIntExtra("col",0)

        val list = ArrayList<ModelClass>()
        var iCount:Int = 0

        for (i in 0 until countGet) {
            list.add(ModelClass(item = 0,0,0))
        }

        if(list.size % colCount == 0){
            iCount=(list.size / colCount)
        }else{
            iCount=(list.size / colCount) +1
        }

        for (i in 0 until iCount) {
           for(j in 0 until colCount){
               val getpos = i*colCount+j
               if(list.size>getpos){
                   list[getpos].i = i
                   list[getpos].j = j
               }else{
                   break;
               }
           }
        }

        val adaptor  = MainAdaptor(this@SecondActivity,list)
        binding.rvGrid.apply {
            this.adapter = adaptor
            this.layoutManager =GridLayoutManager(this@SecondActivity,colCount,GridLayoutManager.VERTICAL,false)
            this.itemAnimator = DefaultItemAnimator()
        }

        adaptor.itemClick  = { item,position,isLeft,isRight,isTop,isBottom ->
            val isColor = list[position].isColor
            if(!isColor){
                val get = list.filter { it.isColor }
                get.forEach {
                    list[list.indexOf(it)].isColor=false
                }

                val layoutManager:GridLayoutManager = binding.rvGrid.layoutManager as GridLayoutManager
                val hEndPos = layoutManager.spanCount -1
                val hStartPos = 0

                val iMain = list[position].i
                val jMain = list[position].j

                if(iMain==0){
                    setPos(list,"bottom",position)
                    if(jMain!=hStartPos){
                        setPos(list,"left",position)
                    }
                    if(jMain!=hEndPos){
                        setPos(list,"right",position)

                    }
                }else if(iMain==iCount-1 || iMain==iCount){
                    setPos(list,"top",position)
                    if(jMain!=0){
                        setPos(list,"left",position)
                    }
                    if(jMain!=hEndPos || position>list.size-1){
                        setPos(list,"right",position)
                    }
                }else{
                    setPos(list,"bottom",position)
                    setPos(list,"top",position)
                    if(jMain!=0){
                        setPos(list,"left",position)
                    }
                    if(jMain!=hEndPos || position>list.size-1){
                        setPos(list,"right",position)
                    }
                }

            }
           // list[position].isCenter = true
            adaptor.notifyDataSetChanged()
        }

    }

    fun setPos(list:ArrayList<ModelClass>,side:String,position:Int){
        if(side == "left"){
            try{
                val leftPos= position-1
                list[leftPos].isColor=true
            }catch (_:Exception){

            }
        }else if(side == "right"){
            try{
                val rightPos= position+1
                list[rightPos].isColor=true
            }catch (_:Exception){

            }

        }else if(side == "top"){
            try{
                val i = list[position].i - 1
                val j = list[position].j
                val pos = list.find { it.i ==  i && it.j == j}
                list[list.indexOf(pos)].isColor=true
                val topPos = list.indexOf(pos)
                list[topPos].isColor=true
            }catch (_:Exception){

            }

        }else if(side == "bottom"){
            try{
                val i = list[position].i + 1
                val j = list[position].j
                val pos = list.find { it.i ==  i && it.j == j}
                val bottomPos = list.indexOf(pos)
                list[bottomPos].isColor=true
            }catch (_:Exception){

            }

        }

    }
}