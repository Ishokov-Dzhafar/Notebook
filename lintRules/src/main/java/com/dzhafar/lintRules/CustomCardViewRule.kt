package com.dzhafar.lintRules

import com.android.SdkConstants.ATTR_CONTENT_DESCRIPTION
import com.android.resources.ResourceType
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import com.android.tools.lint.detector.api.XmlScannerConstants
import org.jetbrains.uast.UElement
import org.w3c.dom.Element
import java.util.Collections

class CustomCardViewRule : ResourceXmlDetector() {
    companion object {
        private const val ID = "CustomCardViewRule11"
        private const val BRIEF_DESCRIPTION = "Missing attribute paddingInView/paddingOutView"
        private const val EXPLANATION = "Missing attribute paddingInView/paddingOutView"

        val ISSUE = Issue.create(
            ID,
            BRIEF_DESCRIPTION,
            EXPLANATION,
            Category.CORRECTNESS,
            10,
            Severity.ERROR,
            Implementation(CustomCardViewRule::class.java, Scope.ALL_RESOURCES_SCOPE)
        )
    }

    override fun getApplicableElements(): Collection<String>? {
        return Collections.singletonList("com.dzhafar.notes.presentation.view.CustomCardView")
    }

    override fun getApplicableAttributes(): Collection<String>? {
        return Collections.singletonList(ATTR_CONTENT_DESCRIPTION)
    }
    //override fun getApplicableElements(): List<String> = XmlScannerConstants.ALL

    override fun visitElement(context: XmlContext, element: Element) {
        if (
            !element.hasAttributeNS(
                "http://schemas.android.com/apk/res/com.google.io.demo",
                "paddingInView"
            )
        ) {
            context.report(
                ISSUE,
                element,
                context.getLocation(element),
                "Error missing attribute paddingInView/paddingOutView"
            )
        }
        /*if (element.tagName == "com.dzhafar.notes.presentation.view.CustomCardView") {
            val isValid =
                element.hasAttribute("paddingInView") && element.hasAttribute("paddingOutView")
            if (isValid) {
                context.report(
                    ISSUE,
                    element,
                    context.getLocation(element),
                    "Error missing attribute paddingInView/paddingOutView"
                )
            }
        }*/
    }

}