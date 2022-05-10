package com.example.dungeonans.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.dungeonans.Adapter.PostCommentCardViewAdapter
import com.example.dungeonans.DataClass.AnswerData
import com.example.dungeonans.DataClass.PostCommentData
import com.example.dungeonans.R
import retrofit2.http.Body

class PostActivity : AppCompatActivity() {
    var commentData : MutableList<PostCommentData> = mutableListOf()
    var answerData : MutableList<AnswerData> = mutableListOf()

    private var doubleBackToExitPressedOnce = false

    lateinit var commentEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ask_post_fragment)

        commentEditText = findViewById(R.id.commentEditText)
        commentEditText.setOnClickListener{
            commentEditText.requestFocus()
        }

        var writeCommentBtn : ImageButton = findViewById(R.id.writeCommentBtn)
        writeCommentBtn.setOnClickListener {
            var bodyValue = commentEditText.text.toString()
            putComment(bodyValue,commentEditText)
            commentEditText.text.clear()
            commentEditText.clearFocus()
            var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(commentEditText.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        var answerBtn : Button = findViewById(R.id.answerBtn)
        answerBtn.setOnClickListener{
            commentEditText.hint = "답변을 입력하세요"
            commentEditText.requestFocus()
            var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            manager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT)

            setRecyclerView = 1
        }

        renderCommentUi(commentEditText)
        renderAnswerUi()
    }

    private fun renderAnswerUi() {
        recyclerView = findViewById(R.id.postAnswerRecyclerView)
        var data : MutableList<AnswerData> = setAnswerData()
        var adapter = PostAnswerCardViewAdapter()
        adapter.setItemClickListener(object : PostAnswerCardViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                var intent = Intent(this@PostActivity,AnswerActivity::class.java)
                startActivity(intent)
            }
        })

        adapter.listData = data
        recyclerView.adapter = adapter
        LinearLayoutManager(this).also { recyclerView.layoutManager = it }

    }

    private fun setAnswerData() : MutableList<AnswerData> {
        for (index in 0 until 2) {
            var commentWriteProfile = R.drawable.profile_base_icon
            var commentWriterName = "${index}번째 작성자"
            var commentWriterNickname = "(@yongkingg)"
            var commentWriteTime = "03/21 12:45"
            var commentBody = "안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요"
            var listData = AnswerData(commentWriteProfile,commentWriterName,commentWriterNickname,commentWriteTime,commentBody)
            answerData.add(listData)
        }
        return answerData
    }
    private fun renderCommentUi(commentEditText : EditText) {

        recyclerView = findViewById(R.id.postCommentRecyclerView)

        var data : MutableList<PostCommentData> = setCommentData()
        var adapter = PostCommentCardViewAdapter()
        adapter.setItemClickListener(object : PostCommentCardViewAdapter.OnItemClickListener {
            override fun commentClick(v: View, position: Int) {
                commentPosition = position
                setRecyclerView = 0
>>>>>>> 4c6a4c5 (ADD : 좋아요, 댓글, 답변창 열기 기능)
                commentEditText.requestFocus()
                commentEditText.hint = "대댓글을 작성해보세요"
                var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT)
            }

            override fun likeClick(v: View, position: Int) {
            }
        })

        adapter.listData = data
        recyclerView.adapter = adapter
        LinearLayoutManager(this).also { recyclerView.layoutManager = it }
    }

    private fun renderAnswerUi(commentEditText : EditText) {
        var recyclerView : RecyclerView = findViewById(R.id.postAnswerRecyclerView)
        var data : MutableList<PostCommentData> = setAnswerData()
        var adapter = PostCommentCardViewAdapter()
        adapter.setItemClickListener(object : PostCommentCardViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Log.d("tag",adapter.itemCount.toString())
                commentEditText.requestFocus()
                commentEditText.hint = "답변에 대한 댓글을 작성해보세요"
                var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT)
            }
        })
        adapter.listData = data
        recyclerView.adapter = adapter
        LinearLayoutManager(this).also { recyclerView.layoutManager = it }
    }

    private fun putComment(body: String, commentEditText : EditText) {
        recyclerView = findViewById(R.id.postCommentRecyclerView)
        var data : MutableList<PostCommentData> = putCommentValue(body,commentPosition)
        var adapter = PostCommentCardViewAdapter()
        adapter.setItemClickListener(object : PostCommentCardViewAdapter.OnItemClickListener {
            override fun commentClick(v: View, position: Int) {
                commentPosition = position
                setRecyclerView = 0
                commentEditText.requestFocus()
                commentEditText.hint = "대댓글을 작성해보세요"
                var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT)
            }
            override fun likeClick(v: View, position: Int) {
            }
        })
        adapter.listData = data
        recyclerView.adapter = adapter
    }

    private fun setCommentData() : MutableList<PostCommentData> {
        for (index in 0 until 6) {
            var commentWriteProfile = R.drawable.profile_base_icon
            var commentWriterName = "${index}번째 작성자"
            var commentWriterNickname = "(@yongkingg)"
            var commentWriteTime = "03/21 12:45"
            var commentBody = "안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요"
            var like = 0
            var listData = PostCommentData(commentWriteProfile,commentWriterName,commentWriterNickname,commentWriteTime,commentBody,like)
            commentData.add(listData)
        }
        return commentdata
    }

    private fun setAnswerData() : MutableList<PostCommentData> {
        for (index in 0 until 6) {
            var commentWriteProfile = R.drawable.profile_base_icon
            var commentWriterName = "${index}번째 작성자"
            var commentWriterNickname = "(@yongkingg)"
            var commentWriteTime = "03/21 12:45"
            var commentBody = "안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요"
            var listData = PostCommentData(commentWriteProfile,commentWriterName,commentWriterNickname,commentWriteTime,commentBody)
            answerData.add(listData)
        }
        return answerData
    }

    private fun putCommentValue(body: String) : MutableList<PostCommentData> {
        var commentWriteProfile = R.drawable.profile_base_icon
        var commentWriterName = "번째 작성자"
        var commentWriterNickname = "(@yongkingg)"
        var commentWriteTime = "03/21 12:45"
        var commentBody = body
        var like = 0
        var listData = PostCommentData(commentWriteProfile,commentWriterName,commentWriterNickname,commentWriteTime,commentBody,like)

        try {
            commentData.add(position+1,listData)
        } catch (e : IndexOutOfBoundsException) {
            commentData.add(position,listData)
        }
        return commentData
    }

//

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        commentEditText.hint = "댓글을 입력하세요"
        setRecyclerView = 0
        commentEditText.clearFocus()
        var manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(commentEditText.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}