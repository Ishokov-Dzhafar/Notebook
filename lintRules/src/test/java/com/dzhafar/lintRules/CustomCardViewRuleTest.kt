package com.dzhafar.lintRules

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

class CustomCardViewRuleTest {
    @Test
    fun `missing attribute in CustomCardView`() {
        val filePath = "res/layout/path.xml"
        TestLintTask.lint()
            .allowMissingSdk()
            .files(
                TestFiles.xml(
                    filePath,
                    """
                            <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                                xmlns:app="http://schemas.android.com/apk/res/com.google.io.demo"
                                android:layout_width="match_parent"
                                android:layout_height="match_parent" >                        
                                <com.dzhafar.notes.presentation.view.CustomCardView 
                                    android:id="@+id/customCardView"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content">
                                        
                                    <TextView
                                        android:id="@+id/title" 
                                        android:layout_width="match_parent"
                                        android:layout_height="wrap_content"
                                        android:gravity="start"
                                        android:textStyle="bold" />
                                                        
                                    <TextView
                                        android:id="@+id/body"
                                        android:layout_width="match_parent"
                                        android:layout_height="wrap_content"
                                        android:gravity="start" />
                                                
                                </com.dzhafar.notes.presentation.view.CustomCardView>
                            </FrameLayout>
                            """
                )
            )
            .issues(CustomCardViewRule.ISSUE)
            .run()
            .expect(
                """
$filePath:6: Error: Error missing attribute paddingInView/paddingOutView [CustomCardViewRule]
                                <com.dzhafar.notes.presentation.view.CustomCardView 
                                ^
1 errors, 0 warnings
"""
            )
    }

    @Test
    fun `has all required attribute in CustomCardView`() {
        val filePath = "res/layout/path.xml"
        TestLintTask.lint()
            .allowMissingSdk()
            .files(
                TestFiles.xml(
                    filePath,
                    """
                            <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                                xmlns:app="http://schemas.android.com/apk/res/com.google.io.demo"
                                android:layout_width="match_parent"
                                android:layout_height="match_parent" >                        
                                <com.dzhafar.notes.presentation.view.CustomCardView 
                                    android:id="@+id/customCardView"
                                    app:paddingInView="16dp"
                                    app:paddingOutView="16dp"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content">
                                        
                                    <TextView
                                        android:id="@+id/title" 
                                        android:layout_width="match_parent"
                                        android:layout_height="wrap_content"
                                        android:gravity="start"
                                        android:textStyle="bold" />
                                                        
                                    <TextView
                                        android:id="@+id/body"
                                        android:layout_width="match_parent"
                                        android:layout_height="wrap_content"
                                        android:gravity="start" />
                                                
                                </com.dzhafar.notes.presentation.view.CustomCardView>
                            </FrameLayout>
                            """
                )
            )
            .issues(CustomCardViewRule.ISSUE)
            .run()
            .expectClean()
    }

    @Test
    fun `missing attribute paddingInView in CustomCardView`() {
        val filePath = "res/layout/path.xml"
        TestLintTask.lint()
            .allowMissingSdk()
            .files(
                TestFiles.xml(
                    filePath,
                    """
                            <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                                xmlns:app="http://schemas.android.com/apk/res/com.google.io.demo"
                                android:layout_width="match_parent"
                                android:layout_height="match_parent" >                        
                                <com.dzhafar.notes.presentation.view.CustomCardView 
                                    android:id="@+id/customCardView"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    app:paddingOutView="16dp">
                                        
                                    <TextView
                                        android:id="@+id/title" 
                                        android:layout_width="match_parent"
                                        android:layout_height="wrap_content"
                                        android:gravity="start"
                                        android:textStyle="bold" />
                                                        
                                    <TextView
                                        android:id="@+id/body"
                                        android:layout_width="match_parent"
                                        android:layout_height="wrap_content"
                                        android:gravity="start" />
                                                
                                </com.dzhafar.notes.presentation.view.CustomCardView>
                            </FrameLayout>
                            """
                )
            )
            .issues(CustomCardViewRule.ISSUE)
            .run()
            .expect(
                """
$filePath:6: Error: Error missing attribute paddingInView/paddingOutView [CustomCardViewRule]
                                <com.dzhafar.notes.presentation.view.CustomCardView 
                                ^
1 errors, 0 warnings
"""
            )
    }

    @Test
    fun `missing attribute paddingOutView in CustomCardView`() {
        val filePath = "res/layout/path.xml"
        TestLintTask.lint()
            .allowMissingSdk()
            .files(
                TestFiles.xml(
                    filePath,
                    """
                            <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                                xmlns:app="http://schemas.android.com/apk/res/com.google.io.demo"
                                android:layout_width="match_parent"
                                android:layout_height="match_parent" >                        
                                <com.dzhafar.notes.presentation.view.CustomCardView 
                                    android:id="@+id/customCardView"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    app:paddingInView="16dp">
                                        
                                    <TextView
                                        android:id="@+id/title" 
                                        android:layout_width="match_parent"
                                        android:layout_height="wrap_content"
                                        android:gravity="start"
                                        android:textStyle="bold" />
                                                        
                                    <TextView
                                        android:id="@+id/body"
                                        android:layout_width="match_parent"
                                        android:layout_height="wrap_content"
                                        android:gravity="start" />
                                                
                                </com.dzhafar.notes.presentation.view.CustomCardView>
                            </FrameLayout>
                            """
                )
            )
            .issues(CustomCardViewRule.ISSUE)
            .run()
            .expect(
                """
$filePath:6: Error: Error missing attribute paddingInView/paddingOutView [CustomCardViewRule]
                                <com.dzhafar.notes.presentation.view.CustomCardView 
                                ^
1 errors, 0 warnings
"""
            )
    }
}