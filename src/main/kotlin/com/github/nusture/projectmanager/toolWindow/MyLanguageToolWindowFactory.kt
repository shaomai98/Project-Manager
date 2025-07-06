package com.github.nusture.projectmanager.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import java.util.*
import javax.swing.JPanel
import com.intellij.openapi.util.IconLoader
import com.github.nusture.projectmanager.MyBundle
import javax.swing.JButton
import javax.swing.BoxLayout
import java.awt.Color
import java.awt.FlowLayout
import javax.swing.border.EmptyBorder
import java.awt.Dimension

class MyLanguageToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = JBPanel<JBPanel<*>>()
        panel.layout = FlowLayout(FlowLayout.LEFT, 2, 2) // 左对齐，水平间距2，垂直间距2
        panel.border = EmptyBorder(0, 0, 0, 0)

        val iconNames = listOf(
            "save" to "/icons/save.svg",
            "edit" to "/icons/edit.svg",
            "bookmarks" to "/icons/bookmarks.svg",
            "showAsTree" to "/icons/showAsTree.svg"
        )
        iconNames.forEach { (key, path) ->
            val icon = IconLoader.getIcon(path, javaClass)
            val button = JButton(icon)
            button.isFocusPainted = false
            button.isContentAreaFilled = false
            button.isBorderPainted = false
            button.margin = java.awt.Insets(0, 0, 0, 0)
            button.toolTipText = MyBundle.message(key)
            button.setRolloverEnabled(true)
            button.setOpaque(true)
            button.background = Color(0, 0, 0, 0)
            button.preferredSize = Dimension(24, 24)
            button.addMouseListener(object : java.awt.event.MouseAdapter() {
                override fun mouseEntered(e: java.awt.event.MouseEvent?) {
                    button.background = Color(60, 60, 60) // 更改背景颜色
                    button.border = javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED) // 添加阴影效果
                    // 使用不同的图标或调整图标的颜色
                    button.icon = IconLoader.getIcon(path.replace("svg", "hover.svg"), javaClass) // 使用 hover 状态的图标
                }
                override fun mouseExited(e: java.awt.event.MouseEvent?) {
                    button.background = Color(0, 0, 0, 0) // 恢复背景颜色
                    button.border = null // 移除阴影效果
                    button.icon = IconLoader.getIcon(path, javaClass) // 恢复原始图标
                }
            })
            panel.add(button)
        }

        val content = ContentFactory.getInstance().createContent(panel, null, false)
        toolWindow.contentManager.addContent(content)
    }
}