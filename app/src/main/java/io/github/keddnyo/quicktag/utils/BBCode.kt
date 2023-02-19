package io.github.keddnyo.quicktag.utils

class BBCode {
    fun decode(content: String): String {

        var newContent = content

        val codeMap = HashMap<String, String>()

        codeMap["\r\n"] = "<br/>"
        codeMap["\\[(?i)b\\](.+?)\\[/(?i)b\\]"] = "<strong>$1</strong>"
        codeMap["\\[(?i)i\\](.+?)\\[/(?i)i\\]"] = "<em>$1</em>"
        codeMap["\\[(?i)u\\](.+?)\\[/(?i)u\\]"] = "<u>$1</u>"
        codeMap["\\[(?i)quote\\](.+?)\\[/(?i)quote\\]"] = "<blockquote>$1</blockquote>"
        codeMap["\\[(?i)center\\](.+?)\\[/(?i)center\\]"] = "<div style='text-align:center'>$1</div>"
        codeMap["\\[(?i)color=(.+?)\\](.+?)\\[/(?i)color\\]"] = "<span style='color:$1;'>$2</span>"
        codeMap["\\[(?i)size=(.+?)\\](.+?)\\[/(?i)size\\]"] = "<span style='font-size:$1;'>$2</span>"
        codeMap["\\[(?i)img\\](.+?)\\[/(?i)img\\]"] = "<strong>Изображение</strong>"
        codeMap["\\[(?i)url=(.+?)\\](.+?)\\[/(?i)url\\]"] = "<a href='$1'>$2</a>"
        codeMap["\\[(?i)list\\]"] = ""
        codeMap["\\[(?i)list=1\\]"] = ""
        codeMap["\\[/(?i)list\\]"] = ""
        codeMap["\\[\\*\\]"] = "• "
        codeMap["\\[(?i)url=\"(.+?)\"\\](.+?)\\[/(?i)url\\]"] = "<a href='$1'>$2</a>"

        for (i in codeMap) {
            newContent = newContent.replace(i.key.toRegex(), i.value)
        }

        return newContent.trim()
    }
}