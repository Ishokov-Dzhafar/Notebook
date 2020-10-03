package com.dzhafar.lintRules

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Element

class CustomCardViewRule : ResourceXmlDetector() {
    companion object {
        private const val ID = "CustomCardViewRule"
        private const val BRIEF_DESCRIPTION = "Missing attribute paddingInView/paddingOutView"
        private const val EXPLANATION = "Missing attribute paddingInView/paddingOutView"

        val ISSUE = Issue.create(
            ID,
            BRIEF_DESCRIPTION,
            EXPLANATION,
            Category.CORRECTNESS,
            10,
            Severity.ERROR,
            Implementation(CustomCardViewRule::class.java, Scope.RESOURCE_FILE_SCOPE)
        )
    }

    override fun getApplicableElements(): Collection<String>? {
        return listOf("com.dzhafar.notes.presentation.view.CustomCardView")
    }

    override fun visitElement(context: XmlContext, element: Element) {
        if (element.tagName == "com.dzhafar.notes.presentation.view.CustomCardView") {
            val isValid =
                element.hasAttributeNS("http://schemas.android.com/apk/res/com.google.io.demo", "paddingInView") &&
                    element.hasAttributeNS("http://schemas.android.com/apk/res/com.google.io.demo", "paddingOutView")
            if (!isValid) {
                context.report(
                    ISSUE,
                    element,
                    context.getLocation(element),
                    "Error missing attribute paddingInView/paddingOutView"
                )
            }
        }
    }

}