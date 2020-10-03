package com.dzhafar.lintRules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.intellij.psi.PsiParameter
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UExpression
import org.jetbrains.uast.asRecursiveLogString
import org.jetbrains.uast.getContainingUMethod

// НА ЭТОТ РУЛ ПОКА НЕ ОБРАЩАЙТЕ ВНИМАНИЕ - ОН ЕЩЕ НЕ ДОДЕЛАН

class CatchErrorFlow : Detector(), Detector.UastScanner {
    companion object {
        const val ID = "CatchErrorFlow"
        const val BRIEF_DESCRIPTION = "Catch error was forget for Flow"
        const val EXPLANATION = "Add catch function for Flow"

        val ISSUE = Issue.create(
            ID,
            BRIEF_DESCRIPTION,
            EXPLANATION,
            Category.CORRECTNESS,
            10,
            Severity.ERROR,
            Implementation(CatchErrorFlow::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes() = listOf(UCallExpression::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return MissingCatchError(context)
    }

    class MissingCatchError(
        private val context: JavaContext
    ) : UElementHandler() {

        override fun visitCallExpression(node: UCallExpression) {
            val uMethod = node.getContainingUMethod()?.psi
            if (node.methodIdentifier?.name == "collect") {
                println("COLLECT")
                print("-------------------")
                println(node.asRecursiveLogString())
                println(node.receiver?.asRecursiveLogString())
                print("-------------------")
            }
            if (context.evaluator.isMemberInClass(uMethod, "kotlinx.coroutines.flow.Flow")) {
                node.resolve()?.let { resolvedMethod ->
                    val mapping: Map<UExpression, PsiParameter> =
                        context.evaluator.computeArgumentMapping(node, resolvedMethod)
                    for (parameter: PsiParameter in mapping.values) {
                        if ("catch" == parameter.name) {
                            // catch has been supplied
                            return
                        }
                    }
                    report(context, node)
                }
            }
        }

        private fun report(context: JavaContext, node: UElement) {
            context.report(
                ISSUE,
                node,
                context.getLocation(node),
                "Error missing catch function on Flow"
            )
        }
    }


    /*override fun visitMethod(
        context: JavaContext,
        visitor: JavaElementVisitor?,
        call: PsiMethodCallExpression,
        method: PsiMethod
    ) {
        context.evaluator.isMemberInClass(method, "kotlinx.coroutines.flow.Flow")
    }*/

    /*override fun visitMethod(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        if (context.evaluator.isMemberInClass(method, "kotlinx.coroutines.flow.Flow")) {
            if (isErrorSuppressingOperator(node.receiver)) {
                super.visitMethod(context, node, method)
                return
            }
            node.resolve()?.let { resolvedMethod ->
                val mapping: Map<UExpression, PsiParameter> =
                    context.evaluator.computeArgumentMapping(node, resolvedMethod)
                for (parameter: PsiParameter in mapping.values) {
                    if ("catch" == parameter.name) {
                        // catch has been supplied
                        super.visitMethod(context, node, method)
                        return
                    }
                }
                report(context, node)
            }
        }
        super.visitMethod(context, node, method)
    }*/

    /*override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        if (context.evaluator.isMemberInClass(method, "kotlinx.coroutines.flow.Flow")) {
            if (isErrorSuppressingOperator(node.receiver)) {
                return
            }
            node.resolve()?.let { resolvedMethod ->
                val mapping: Map<UExpression, PsiParameter> =
                    context.evaluator.computeArgumentMapping(node, resolvedMethod)
                for (parameter: PsiParameter in mapping.values) {
                    if ("catch" == parameter.name) {
                        // catch has been supplied
                        return
                    }
                }
                report(context, node)
            }
        }
    }

    private fun report(context: JavaContext, node: UElement) {
        context.report(
            ISSUE,
            node,
            context.getLocation(node),
            "Error missing catch function on Flow"
        )
    }

    private fun isErrorSuppressingOperator(
        receiver: UExpression?
    ): Boolean {
        val methods = "kotlinx.coroutines.flow.collect"
        if (receiver == null) {
            return false
        }
        val element: PsiElement = receiver.sourcePsi!!
        return (element is PsiMethod && methods == element.returnType?.canonicalText)
    }*/
}