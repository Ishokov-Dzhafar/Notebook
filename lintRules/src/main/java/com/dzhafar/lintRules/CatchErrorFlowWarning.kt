package com.dzhafar.lintRules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.getUCallExpression
import org.jetbrains.uast.visitor.AbstractUastVisitor

class CatchErrorFlowWarning : Detector(), Detector.UastScanner {

    companion object {
        const val ID = "CatchErrorFlowWarning"
        const val BRIEF_DESCRIPTION = "Catch error was forget for Flow"
        const val EXPLANATION = "Add catch function for Flow"

        val ISSUE = Issue.create(
            ID,
            BRIEF_DESCRIPTION,
            EXPLANATION,
            Category.CORRECTNESS,
            10,
            Severity.ERROR,
            Implementation(CatchErrorFlowWarning::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes() = listOf(UClass::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return MissingCatchErrorFlowVisitor(context)
    }

    class MissingCatchErrorFlowVisitor(
        private val context: JavaContext
    ) : UElementHandler() {

        override fun visitClass(node: UClass) {
            val methods = node
            val flowField = node.allMethods.filter {
                it.returnType?.canonicalText == "kotlinx.coroutines.flow.collect"
            }.toMutableSet()
            // val flowField = node.references
            node.accept(object : AbstractUastVisitor() {
                override fun visitCallExpression(node: UCallExpression): Boolean {
                    return if (node.methodName == "catch") {
                        val iterator = flowField.iterator()
                        while (iterator.hasNext()) {
                            if (node.receiver?.asRenderString() == iterator.next().name) {
                                iterator.remove()
                            }
                        }
                        true
                    } else {
                        super.visitCallExpression(node)
                    }
                }
            })
            flowField.forEach {
                context.report(
                    ISSUE,
                    it,
                    context.getLocation(it),
                    "Error missing catch function on Flow"
                )
            }
        }
    }


}