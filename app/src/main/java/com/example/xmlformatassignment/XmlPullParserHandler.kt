package com.example.xmlformatassignment

import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XmlPullParserHandler {
    private val studentdetails = ArrayList<Student>()
    private var student: Student? = null
    private var text: String? = null

    fun parse(inputStream: InputStream): List<Student> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagname = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagname.equals("student", ignoreCase = true)) {
                        student = Student()
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if (tagname.equals("student", ignoreCase = true)) {
                        student?.let { studentdetails.add(it) }
                    } else if (tagname.equals("id", ignoreCase = true)) {
                        student!!.id = text
                    } else if (tagname.equals("name", ignoreCase = true)) {
                        student!!.name = text
                    } else if (tagname.equals("marks", ignoreCase = true)) {
                        student!!.marks = java.lang.Float.parseFloat(text)
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return studentdetails
    }
}