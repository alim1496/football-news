package com.appwiz.football_news_videos.utils


class UtilMethods {

    fun findTimeAgo(given:Long) : String {
        val now = System.currentTimeMillis()
        val diff = ((now - given)/1000).toDouble()

        var tDif = ""
        var iDif = 0
        if (diff < 60) {
            iDif = diff.toInt()
            if (iDif == 1) tDif = "$iDif second ago"
            else tDif = "$iDif seconds ago"
        } else if (diff < 3600) {
            iDif = (diff/60).toInt()
            if (iDif == 1) tDif = "$iDif minute ago"
            else tDif = "$iDif minutes ago"
        } else if (diff < 86400) {
            iDif = (diff/3600).toInt()
            if (iDif == 1) tDif = "$iDif hour ago"
            else tDif = "$iDif hours ago"
        } else if (diff < 2592000) {
            iDif = (diff/86400).toInt()
            if (iDif == 1) tDif = "$iDif day ago"
            else tDif = "$iDif days ago"
        } else {
            iDif = (diff/2592000).toInt()
            if (iDif == 1) tDif = "$iDif month ago"
            else tDif = "$iDif months ago"
        }

        return tDif
    }

}