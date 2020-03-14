package com.example.temp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.shockwave.pdfium.PdfDocument

class PDFActivity : AppCompatActivity(), OnPageChangeListener, OnLoadCompleteListener {
    var pdfView: PDFView? = null
    internal var pageNumber: Int? = 0
    internal var pdfFileName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)


        pdfView = findViewById(R.id.pdfView)
        displayFromAsset(SAMPLE_FILE)
    }

    private fun displayFromAsset(assetFileName: String) {
        pdfFileName = assetFileName

        pdfView!!.fromAsset(SAMPLE_FILE)
            .defaultPage(pageNumber!!)
            .enableSwipe(true)

            .swipeHorizontal(false)
            .onPageChange(this)
            .enableAnnotationRendering(true)
            .onLoad(this)
            .scrollHandle(DefaultScrollHandle(this))
            .load()
    }


    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
        title = String.format("%s %s / %s", pdfFileName, page + 1, pageCount)
    }


    override fun loadComplete(nbPages: Int) {
        val meta = pdfView!!.documentMeta
        printBookmarksTree(pdfView!!.tableOfContents, "-")

    }

    fun printBookmarksTree(tree: List<PdfDocument.Bookmark>, sep: String) {
        for (b in tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.title, b.pageIdx))

            if (b.hasChildren()) {
                printBookmarksTree(b.children, "$sep-")
            }
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        val SAMPLE_FILE = "CutOff.pdf"
    }

}