package com.github.kusa233.ovatoxin.samples.full

import com.github.kusa233.ovatoxin.component.SurfaceScope
import com.github.kusa233.ovatoxin.layout.percent
import com.github.kusa233.ovatoxin.layout.px
import com.github.kusa233.ovatoxin.state.State
import com.github.kusa233.ovatoxin.widget.action.Button
import com.github.kusa233.ovatoxin.widget.container.Align
import com.github.kusa233.ovatoxin.widget.container.Card
import com.github.kusa233.ovatoxin.widget.container.CollapsePane
import com.github.kusa233.ovatoxin.widget.container.Div
import com.github.kusa233.ovatoxin.widget.container.FlexBox
import com.github.kusa233.ovatoxin.widget.container.Justify
import com.github.kusa233.ovatoxin.widget.container.TabPane
import com.github.kusa233.ovatoxin.widget.container.TabSide
import com.github.kusa233.ovatoxin.widget.container.button
import com.github.kusa233.ovatoxin.widget.container.card
import com.github.kusa233.ovatoxin.widget.container.collapsePane
import com.github.kusa233.ovatoxin.widget.container.flexFlow
import com.github.kusa233.ovatoxin.widget.container.text
import com.github.kusa233.ovatoxin.widget.feedback.Toast
import com.github.kusa233.ovatoxin.widget.feedback.ToastPosition
import com.github.kusa233.ovatoxin.widget.feedback.ToastTone
import com.github.kusa233.ovatoxin.widget.input.CheckBox
import com.github.kusa233.ovatoxin.widget.input.ComboBox
import com.github.kusa233.ovatoxin.widget.input.TextField
import com.github.kusa233.ovatoxin.widget.list.ListView
import com.github.kusa233.ovatoxin.widget.progress.ProgressBar
import com.github.kusa233.ovatoxin.widget.progress.progressBar
import com.github.kusa233.ovatoxin.widget.text.Link
import com.github.kusa233.ovatoxin.widget.text.bareTextArea
import com.github.kusa233.ovatoxin.window.Frame
import javafx.application.Platform
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

// ---------------------------------------------------------------------------------------------
// Palette
// ---------------------------------------------------------------------------------------------

private val PAGE_BG: Color = Color.web("#f4f6fb")
private val INK: Color = Color.web("#162033")
private val MUTED: Color = Color.web("#6b7280")
private val FAINT: Color = Color.web("#98a2b3")
private val LINE: Color = Color.web("#e4e9f2")
private val BRAND: Color = Color.web("#4c8bf5")
private val BRAND_DEEP: Color = Color.web("#2563eb")
private val BRAND_SOFT: Color = Color.web("#e8f0ff")
private val MINT: Color = Color.web("#10b981")
private val AMBER: Color = Color.web("#f59e0b")
private val VIOLET: Color = Color.web("#8b5cf6")
private val ROSE: Color = Color.web("#f43f5e")
private val NAVY: Color = Color.web("#101b33")
private val TRACK: Color = Color.web("#e8edf7")
private val ON_DARK: Color = Color.web("#e8f1ff")

private data class Feature(
    val glyph: String,
    val name: String,
    val description: String,
    val tab: Int,
    val accent: Color,
)

private data class ListEntry(
    val glyph: String,
    val name: String,
    val detail: String,
)

private val FEATURES = listOf(
    Feature("▤", "TextArea", "只读文本块，支持换行与对齐。", 1, BRAND),
    Feature("⌨", "TextField", "单行输入、占位提示与输入监听。", 2, MINT),
    Feature("⬢", "Button", "扁平按钮、圆角、禁用与点击反馈。", 3, AMBER),
    Feature("◐", "ProgressBar", "圆角进度条，可自定义前景色。", 4, VIOLET),
    Feature("☰", "ListView", "可选中的滚动列表与详情联动。", 5, ROSE),
    Feature("☑", "CheckBox", "勾选状态与 onChange 事件。", 6, Color.web("#0ea5e9")),
    Feature("▦", "FlexBox", "自动换行的流式布局与间距。", 7, Color.web("#14b8a6")),
    Feature("▭", "Div", "百分比作用域、嵌套面板与裁剪。", 8, Color.web("#6366f1")),
    Feature("▢", "Card", "圆角面板、内边距与自定义描边。", 9, Color.web("#64748b")),
    Feature("▥", "CollapsePane", "可收起/展开、内容区自带纵向滚动的面板。", 10, Color.web("#0ea5e9")),
    Feature("≣", "ComboBox", "可下拉选择的输入字段与实时项。", 11, Color.web("#d946ef")),
    Feature("↗", "Link", "打开 URL 或自定义点击行为的文字链接。", 12, Color.web("#2563eb")),
    Feature("◍", "Toast", "自动消失的浮层提示，按类型着色。", 13, Color.web("#0f766e")),
)

private val ENTRIES = listOf(
    ListEntry("◈", "Ovatoxin", "基于 Kotlin DSL 的 JavaFX 组件集合"),
    ListEntry("▤", "TextArea", "只读文本与说明文字的最佳载体"),
    ListEntry("⌨", "TextField", "轻量、可编辑的单行输入框"),
    ListEntry("⬢", "Button", "带圆角与过渡的扁平按钮"),
    ListEntry("◐", "ProgressBar", "支持前景色定制的进度反馈"),
    ListEntry("☰", "ListView", "可滚动、可选择的数据列表"),
    ListEntry("☑", "CheckBox", "带状态回调的勾选控件"),
    ListEntry("▦", "FlexBox", "从左到右自动换行的布局容器"),
    ListEntry("▭", "Div", "以百分比为基准的嵌套绘制面板"),
    ListEntry("▢", "Card", "带内边距与圆角的卡片容器"),
    ListEntry("▥", "CollapsePane", "可收起、展开并滚动的面板"),
    ListEntry("≣", "ComboBox", "可下拉选择的输入字段"),
    ListEntry("↗", "Link", "点击打开网址的超链接文字"),
    ListEntry("◍", "Toast", "自动消失、按类型着色的浮层提示"),
)

/**
 * A short usage snippet for every component page, shown in a collapsed [CollapsePane] at the bottom
 * of the page. The key is the page [title][Feature.name]; the main overview has no entry.
 */
private val CODE_SAMPLES: Map<String, String> = mapOf(
    "TextArea" to """
        draw(TextArea("Hello, Ovatoxin")) { area ->
            layout {
                width = 100.percent
                height = 120.px
            }
            style {
                foreground = Color.web("#162033")
                fontSize = 15.px
            }
            area.wrap = true
            area.align = TextAlignment.LEFT
        }
    """,
    "TextField" to """
        val field = draw(TextField()) { f ->
            layout {
                width = 100.percent
                height = 42.px
            }
            f.hint = "请输入姓名"
            f.onChange { value -> println(value) }
        }
    """,
    "Button" to """
        button(
            label = "主要按钮",
            width = 120.px,
            height = 42.px,
        ) {
            println("clicked")
        }
    """,
    "ProgressBar" to """
        val bar = draw(ProgressBar()) { p ->
            layout {
                width = 100.percent
                height = 16.px
            }
            style {
                background { color = Color.web("#e8edf7") }
                foreground = Color.web("#4c8bf5")
                corner = 50.percent
            }
            p.progress(60.percent)
        }
    """,
    "ListView" to """
        val list = draw(ListView(entries) { entry -> entry.name }) { view ->
            layout {
                width = 100.percent
                height = 320.px
            }
            view.selectedBackground = Color.web("#dce9ff")
        }
        list.onSelectItem { entry -> println(entry.name) }
    """,
    "CheckBox" to """
        val box = draw(CheckBox("启用动画")) { cb ->
            layout {
                width = 100.percent
                height = 36.px
            }
            cb.onChange { checked -> println(checked) }
        }
    """,
    "FlexBox" to """
        val flow = draw(FlexBox()) { box ->
            layout {
                width = 100.percent
                height = 180.px
            }
            box.gap = 10.px
            box.align = Align.CENTER
            box.scrollY = true
            box.layout {
                button("Chip 1", width = 82.px, height = 36.px)
                button("Chip 2", width = 82.px, height = 36.px)
            }
        }
    """,
    "Div" to """
        val panel = draw(Div()) { side ->
            layout {
                width = 100.percent
                height = 220.px
            }
            style {
                background { color = Color.WHITE }
                corner = 12.px
            }
            side.scrollY = true
            side.layout {
                text("可滚动的内容", height = 24.px, size = 15.0)
                text("更多内容……", height = 24.px, size = 15.0)
            }
        }
    """,
    "Card" to """
        card(height = 180.px, pad = 18.0, gap = 8.0) {
            text("卡片标题", height = 24.px, size = 17.0, bold = true)
            text("卡片正文", height = 20.px, size = 13.0)
            button(label = "详情", width = 96.px, height = 36.px)
        }
    """,
    "CollapsePane" to """
        val detail = collapsePane(
            title = "示例代码",
            expandedHeight = 300.px,
            fill = Color.web("#101b33"),
            headerForeground = Color.web("#e8f1ff"),
            bodyPadding = 12.0,
            bodyGap = 0.0,
        ) {
            draw(TextArea("val answer = 42")) { code ->
                layout {
                    width = 100.percent
                    height = 120.px
                }
            }
        }
        // 也可以在任何时候代码切换：
        detail.toggle()
    """,
    "ComboBox" to """
        val combo = draw(ComboBox(listOf("A", "B", "C"))) { c ->
            layout {
                width = 100.percent
                height = 42.px
            }
            c.onSelectItem { item -> println(item) }
        }
    """,
    "Link" to """
        val link = draw(Link("访问官网", "https://example.com")) { l ->
            layout {
                width = 100.percent
                height = 24.px
            }
            style {
                foreground = Color.web("#2563eb")
                fontSize = 14.px
            }
        }
    """,
    "Toast" to """
        val toast = Toast(duration = 2600.milliseconds)
        toast.show(
            text = "已保存",
            position = ToastPosition.TOP_CENTER,
            tone = ToastTone.SUCCESS,
        )
    """,
)

// ---------------------------------------------------------------------------------------------
// The sample itself
// ---------------------------------------------------------------------------------------------

object ComponentsSample {
    @JvmStatic
    fun main(args: Array<String>) {
        val frame = Frame(
            title = "Ovatoxin · 组件总览",
            width = 1120.0,
            height = 760.0,
            titleBarEnabled = false,
            minWidth = 960.0,
            minHeight = 780.0,
        )
        drawBasePane(frame)
        frame.show()
    }

    fun drawBasePane(frame: Frame) {
        val toast = Toast(duration = 2600.milliseconds)
        frame.layout {
            draw(TabPane()) { pane ->
                layout {
                    width = 100.percent
                    height = 100.percent
                }
                style {
                    background {
                        color = NAVY
                    }
                    foreground = Color.web("#cbd5e1")
                    fontSize = 14.px
                    transition = 120.milliseconds
                }
                pane.selectColor {
                    background = Color.WHITE
                    foreground = BRAND_DEEP
                }
                pane.windowDragEnabled = true
                pane.side = TabSide.LEFT
                pane.barSize = 220.px
                pane.tab.applyLayout {
                    hPadding = 14.px
                    vPadding = 22.px
                }
                pane.tab.gap = 8.px

                bindMain(pane)
                bindTextArea(pane)
                bindTextField(pane)
                bindButton(pane)
                bindProgressBar(pane)
                bindListView(pane)
                bindCheckBox(pane)
                bindFlexBox(pane)
                bindDiv(pane)
                bindCard(pane)
                bindCollapsePane(pane)
                bindComboBox(pane)
                bindLink(pane)
                bindToast(pane, toast)
                pane.select(0)
            }

            // Drawn after the TabPane so the toast layer sits above every page and its own controls.
            draw(toast) {
                layout {
                    width = 100.percent
                    height = 100.percent
                }
            }
        }
    }

    /**
     * Adds one left-hand navigation entry and its page scaffold.
     *
     * The scaffold keeps every page on the same grid: an eyebrow, a headline, a quiet subtitle, then
     * whatever the caller draws in [body].
     */
    private fun TabPane.addPage(
        tabLabel: String,
        title: String,
        subtitle: String,
        body: SurfaceScope<FlexBox>.() -> Unit,
    ): Button {
        val tab = add(Button(tabLabel))
        tab.applyLayout {
            height = 44.px
        }
        tab.applyStyle {
            background {
                color = Color.TRANSPARENT
            }
            foreground = Color.web("#cbd5e1")
            corner = 10.px
            fontSize = 14.px
        }
        bind(tab, FlexBox()) { box ->
            layout {
                hPadding = 28.px
                vPadding = 26.px
            }
            style {
                background {
                    color = PAGE_BG
                }
                foreground = INK
                fontSize = 14.px
            }
            box.gap = 12.px
            box.align = Align.START
            // A page can grow past the window when its code sample is expanded, so the whole page
            // scrolls instead of clipping the sample.
            box.scrollY = true
            box.layout {
                text(
                    content = "OVATOXIN  ·  COMPONENTS",
                    height = 14.px,
                    size = 10.5,
                    color = BRAND,
                    bold = true,
                )
                text(
                    content = title,
                    height = 40.px,
                    size = 29.0,
                    color = INK,
                    bold = true,
                )
                text(
                    content = subtitle,
                    height = 32.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                body()
                CODE_SAMPLES[title]?.let { codeSample(it) }
            }
        }
        return tab
    }

    /**
     * Draws a collapsed code sample using a [CollapsePane] around a read-only [TextArea].
     *
     * The pane starts collapsed, so every page keeps its original shape; expanding it reveals the
     * snippet and lets the page scroll to it. The dark fill and monospaced font keep code visually
     * distinct from the surrounding prose.
     */
    private fun SurfaceScope<*>.codeSample(code: String) {
        val source = code.trimIndent()
        val lineCount = source.lineSequence().count()
        val codeHeight = (lineCount * 17 + 8).coerceAtLeast(48).px
        collapsePane(
            title = "示例代码",
            expandedHeight = 300.px,
            fill = NAVY,
            borderColor = Color.web("#243354"),
            headerForeground = ON_DARK,
            bodyPadding = 12.0,
            bodyGap = 0.0,
        ) {
            bareTextArea(source) { area ->
                layout {
                    width = 100.percent
                    height = codeHeight
                }
                style {
                    foreground = ON_DARK
                    fontSize = 12.px
                }
                area.wrap = false
                area.fontFamily = "Consolas"
            }
        }
    }

    private fun bindMain(pane: TabPane) {
        pane.addPage(
            tabLabel = "◆  Ovatoxin",
            title = "组件总览",
            subtitle = "十三种常用控件与布局容器的完整示例。左侧导航或下方卡片都可以切换页面，点击按钮、输入文字或勾选选项都能看到实时反馈。",
        ) {
            card(
                height = 180.px,
                fill = BRAND,
                borderColor = null,
                pad = 22.0,
                gap = 6.0,
            ) {
                text(
                    content = "KOTLIN  ·  JAVAFX  ·  DECLARATIVE UI",
                    height = 12.px,
                    size = 10.0,
                    color = ON_DARK,
                    bold = true,
                )
                text(
                    content = "用代码构建漂亮的 JavaFX 界面",
                    height = 34.px,
                    size = 26.0,
                    color = Color.WHITE,
                    bold = true,
                )
                text(
                    content = "声明式布局、可组合的组件与状态驱动的重绘，让界面代码保持清晰、直观、易于维护。",
                    height = 34.px,
                    size = 13.0,
                    color = ON_DARK,
                    wrap = true,
                )
                draw(FlexBox()) { row ->
                    style {
                        background {
                            color = Color.TRANSPARENT
                        }
                    }
                    layout {
                        width = 100.percent
                        height = 36.px
                    }
                    row.gap = 10.px
                    row.align = Align.CENTER
                    row.layout {
                        button(
                            label = "开始探索",
                            width = 130.px,
                            height = 34.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                        ) {
                            pane.select(1)
                        }
                        button(
                            label = "退出应用",
                            width = 104.px,
                            height = 34.px,
                            fill = Color.TRANSPARENT,
                            foreground = Color.WHITE,
                            borderColor = Color.web("#a9c7ff"),
                        ) {
                            Platform.exit()
                        }
                    }
                }
            }

            for (feature in FEATURES) {
                card(
                    height = 120.px,
                    width = 23.percent,
                    pad = 14.0,
                    gap = 5.0,
                ) {
                    draw(Div()) {
                        layout {
                            width = 28.px
                            height = 4.px
                        }
                        style {
                            background {
                                color = feature.accent
                            }
                            corner = 50.percent
                        }
                    }
                    text(
                        content = feature.name,
                        height = 20.px,
                        size = 15.0,
                        color = INK,
                        bold = true,
                    )
                    text(
                        content = feature.description,
                        height = 36.px,
                        size = 11.5,
                        color = MUTED,
                        wrap = true,
                    )
                    text(
                        content = "查看示例",
                        height = 16.px,
                        size = 11.0,
                        color = feature.accent,
                        bold = true,
                    )
                }.onClick {
                    pane.select(feature.tab)
                }
            }
        }
    }

    private fun bindTextArea(pane: TabPane) {
        pane.addPage(
            tabLabel = "▤  TextArea",
            title = "TextArea",
            subtitle = "不可编辑的文本块。支持多行文本、自动换行、粗体与水平对齐，是页面说明文字最常用的载体。",
        ) {
            card(height = 430.px, pad = 22.0, gap = 12.0) {
                text("一个安静的阅读区块", height = 28.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "TextArea 的内容由 text 属性控制；wrap = true 会在宽度不足时自动换行，align 则决定每一行文字的水平位置。",
                    height = 36.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                draw(Div()) {
                    layout {
                        width = 100.percent
                        height = 1.px
                    }
                    style {
                        background {
                            color = LINE
                        }
                    }
                }
                text(
                    content = "“好的界面不是堆叠控件，而是用清晰的层级、克制的颜色和稳定的间距，让内容自己说话。”\n\n" +
                            "这段文字就是一个 TextArea。它没有输入框的边框，也没有按钮的装饰，只负责安静地呈递信息。",
                    height = 160.px,
                    size = 15.0,
                    color = INK,
                    wrap = true,
                )
                card(
                    height = 106.px,
                    fill = Color.web("#f8fafc"),
                    pad = 16.0,
                    gap = 6.0,
                ) {
                    text(
                        "TEXTAREA  ·  com.github.kusa233.ovatoxin.widget.text",
                        height = 16.px,
                        size = 10.5,
                        color = FAINT,
                        bold = true
                    )
                    text(
                        content = "你可以随时修改 text、fontSize、bold、align、wrap，下一次绘制就会生效。",
                        height = 40.px,
                        size = 13.0,
                        color = MUTED,
                        wrap = true,
                    )
                }
            }
        }
    }

    private fun bindTextField(pane: TabPane) {
        pane.addPage(
            tabLabel = "⌨  TextField",
            title = "TextField",
            subtitle = "可编辑的单行输入框。支持占位提示、内容变化监听与回车提交；下面的表单会把输入同步到状态文本。",
        ) {
            val live = State("输入内容会实时显示在这里。")
            card(height = 430.px, pad = 22.0, gap = 10.0) {
                text("创建一个小表单", height = 28.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "每个输入框都有自己的圆角与浅色背景。输入时会触发 onChange，让下方状态文本立刻更新。",
                    height = 34.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                text("姓名", height = 18.px, size = 12.0, color = MUTED, bold = true)
                val nameField = draw(TextField()) { field ->
                    layout {
                        width = 100.percent
                        height = 42.px
                    }
                    style {
                        background {
                            color = Color.web("#f8fafc")
                        }
                        corner = 10.px
                        border {
                            size = 1.px
                            color = LINE
                        }
                        foreground = INK
                        fontSize = 14.px
                    }
                    field.hint = "请输入姓名"
                }
                text("邮箱", height = 18.px, size = 12.0, color = MUTED, bold = true)
                val mailField = draw(TextField()) { field ->
                    layout {
                        width = 100.percent
                        height = 42.px
                    }
                    style {
                        background {
                            color = Color.web("#f8fafc")
                        }
                        corner = 10.px
                        border {
                            size = 1.px
                            color = LINE
                        }
                        foreground = INK
                        fontSize = 14.px
                    }
                    field.hint = "name@example.com"
                }
                nameField.onChange {
                    live.update("姓名：${nameField.text()}    邮箱：${mailField.text()}")
                }
                mailField.onChange {
                    live.update("姓名：${nameField.text()}    邮箱：${mailField.text()}")
                }
                draw(FlexBox()) { row ->
                    style {
                        background {
                            color = Color.TRANSPARENT
                        }
                    }
                    layout {
                        width = 100.percent
                        height = 40.px
                    }
                    row.gap = 10.px
                    row.align = Align.CENTER
                    row.layout {
                        button(label = "提交", width = 96.px, height = 38.px) {
                            live.update("已提交：${nameField.text()} · ${mailField.text()}")
                        }
                        button(
                            label = "清空",
                            width = 96.px,
                            height = 38.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                            borderColor = Color.web("#c9d9f7"),
                        ) {
                            nameField.text = ""
                            mailField.text = ""
                            live.update("输入内容会实时显示在这里。")
                        }
                    }
                }
                bareTextArea { status ->
                    layout {
                        width = 100.percent
                        height = 24.px
                    }
                    style {
                        fontSize = 12.0.px
                        foreground = MUTED
                    }
                    status.wrap = true
                    status.text = live.value
                }.withState(live)
            }
        }
    }

    private fun bindButton(pane: TabPane) {
        pane.addPage(
            tabLabel = "⬢  Button",
            title = "Button",
            subtitle = "扁平按钮的几种常见形态：主按钮、描边按钮、胶囊按钮与禁用状态。点击后会更新下面的状态文本。",
        ) {
            val feedback = State("点击任意按钮，这里会显示反馈。")
            card(height = 250.px, pad = 22.0, gap = 10.0) {
                text("按钮样式", height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "圆角、背景色与前景色都来自 style 块；onClick 可以挂在任何组件上。",
                    height = 30.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                draw(FlexBox()) { row ->
                    style {
                        background {
                            color = Color.TRANSPARENT
                        }
                    }
                    layout {
                        width = 100.percent
                        height = 44.px
                    }
                    row.gap = 10.px
                    row.align = Align.CENTER
                    row.layout {
                        button(label = "主要按钮", width = 116.px, height = 42.px) {
                            feedback.update("主要按钮被点击了 ✓")
                        }
                        button(
                            label = "描边按钮",
                            width = 116.px,
                            height = 42.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                            borderColor = Color.web("#b9d1fb"),
                        ) {
                            feedback.update("描边按钮被点击了 ✓")
                        }
                        button(
                            label = "胶囊按钮",
                            width = 116.px,
                            height = 42.px,
                            fill = MINT,
                            radius = 50.percent,
                        ) {
                            feedback.update("胶囊按钮被点击了 ✓")
                        }
                        button(
                            label = "禁用状态",
                            width = 116.px,
                            height = 42.px,
                            fill = Color.web("#e9edf5"),
                            foreground = Color.web("#a2acbd"),
                        ) {
                            feedback.update("这个按钮不应该被点击")
                        }.apply { enabled = false }
                    }
                }
                bareTextArea { status ->
                    layout {
                        width = 100.percent
                        height = 52.px
                    }
                    style {
                        foreground = MUTED
                        fontSize = 13.0.px
                    }
                    status.wrap = true
                    status.text = feedback.value
                }.withState(feedback)
            }
            card(height = 126.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("小提示", height = 18.px, size = 12.0, color = BRAND_DEEP, bold = true)
                text(
                    content = "Button 继承了组件的 onClick 能力；同一种写法也可以作用于 FlexBox、Div 等任意组件。",
                    height = 40.px,
                    size = 13.0,
                    color = Color.web("#355f9f"),
                    wrap = true,
                )
            }
        }
    }

    private fun bindProgressBar(pane: TabPane) {
        val progress = State(0.percent)
        pane.addPage(
            tabLabel = "◐  ProgressBar",
            title = "ProgressBar",
            subtitle = "进度条已经过的部分使用 foreground 指定的颜色，未经过的轨道使用 background。下面有一条自动循环的进度条。",
        ) {
            card(height = 430.px, pad = 22.0, gap = 10.0) {
                text("实时进度", height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "每隔 100ms 更新一次，状态变化只重绘与它绑定的组件。",
                    height = 30.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                draw(FlexBox()) { row ->
                    style {
                        background {
                            color = Color.TRANSPARENT
                        }
                    }
                    layout {
                        width = 100.percent
                        height = 24.px
                    }
                    row.layout {
                        text("自动循环", width = 70.percent, height = 24.px, size = 13.0, color = INK)
                        bareTextArea { percentLabel ->
                            layout {
                                width = 30.percent
                                height = 24.px
                            }
                            style {
                                fontSize = 13.0.px
                                foreground = BRAND
                            }
                            percentLabel.align = TextAlignment.RIGHT
                            percentLabel.bold = true
                            percentLabel.text = "${progress.value.value.toInt()}%"
                        }.withState(progress)
                    }
                }
                draw(ProgressBar()) { bar ->
                    layout {
                        width = 100.percent
                        height = 16.px
                    }
                    style {
                        background {
                            color = TRACK
                        }
                        corner = 50.percent
                        foreground = BRAND
                    }
                    bar.progress(progress.value)
                }.withState(progress)

                draw(Div()) {
                    layout {
                        width = 100.percent
                        height = 1.px
                    }
                    style {
                        background {
                            color = LINE
                        }
                    }
                }
                text("不同的前景色", height = 24.px, size = 15.0, color = INK, bold = true)
                progressBar(h = 14.px, value = 35.percent, tone = MINT, track = TRACK)
                progressBar(h = 14.px, value = 62.percent, tone = AMBER, track = TRACK)
                progressBar(h = 14.px, value = 88.percent, tone = VIOLET, track = TRACK)
                text(
                    content = "前景色由 style { foreground = ... } 控制，轨道颜色则来自 background。",
                    height = 30.px,
                    size = 12.5,
                    color = MUTED,
                    wrap = true,
                )
            }
        }

        Thread.startVirtualThread {
            while (true) {
                progress.update(
                    if (progress.value >= 100.percent) 0.percent else progress.value + 1.percent
                )
                Thread.sleep(100)
            }
        }
    }

    private fun bindListView(pane: TabPane) {
        pane.addPage(
            tabLabel = "☰  ListView",
            title = "ListView",
            subtitle = "可滚动的行列表，支持选中高亮与 onSelectItem 回调。点击左侧的任意一行，右侧详情会同步更新。",
        ) {
            val selected = State<ListEntry?>(ENTRIES.first())
            card(height = 440.px, width = 57.percent, pad = 20.0, gap = 8.0) {
                text("组件目录", height = 24.px, size = 17.0, color = INK, bold = true)
                text("9 个条目  ·  可点击选择", height = 18.px, size = 11.5, color = FAINT)
                val list =
                    draw(ListView(ENTRIES) { entry -> "${entry.glyph}   ${entry.name}      ·   ${entry.detail}" }) { view ->
                        layout {
                            width = 100.percent
                            height = 340.px
                        }
                        style {
                            background {
                                color = Color.web("#f8fafc")
                            }
                            corner = 12.px
                            border {
                                size = 1.px
                                color = LINE
                            }
                            foreground = INK
                            fontSize = 13.0.px
                        }
                        view.selectedBackground = Color.web("#dce9ff")
                    }
                list.onSelectItem { entry -> selected.update(entry) }
                list.select(0)
            }

            card(height = 440.px, width = 41.percent, pad = 22.0, gap = 6.0) {
                text("当前选中", height = 20.px, size = 11.0, color = FAINT, bold = true)
                bareTextArea { icon ->
                    layout {
                        width = 100.percent
                        height = 38.px
                    }
                    style {
                        fontSize = 26.0.px
                        foreground = BRAND
                    }
                    icon.text = selected.value?.glyph ?: "◌"
                }.withState(selected)
                bareTextArea { name ->
                    layout {
                        width = 100.percent
                        height = 30.px
                    }
                    style {
                        fontSize = 21.0.px
                        foreground = INK
                    }
                    name.bold = true
                    name.text = selected.value?.name ?: "未选择"
                }.withState(selected)
                draw(Div()) {
                    layout {
                        width = 40.px
                        height = 3.px
                    }
                    style {
                        background {
                            color = BRAND
                        }
                        corner = 50.percent
                    }
                }
                bareTextArea { detail ->
                    layout {
                        width = 100.percent
                        height = 72.px
                    }
                    style {
                        fontSize = 13.0.px
                        foreground = MUTED
                    }
                    detail.wrap = true
                    detail.text = selected.value?.detail ?: "从左侧列表中选择一项。"
                }.withState(selected)
                text(
                    content = "提示：列表项是一个自定义的 ListEntry 数据类，渲染函数决定它怎么显示。",
                    height = 58.px,
                    size = 12.0,
                    color = FAINT,
                    wrap = true,
                )
            }
        }
    }

    private fun bindCheckBox(pane: TabPane) {
        pane.addPage(
            tabLabel = "☑  CheckBox",
            title = "CheckBox",
            subtitle = "带文字标题的勾选控件。每个选项都可以独立监听 onChange，页面底部的摘要会随选择实时变化。",
        ) {
            val chosen = State(setOf("启用动画"))
            val options = listOf("启用动画", "显示网格", "高对比度", "自动保存")
            card(height = 300.px, pad = 22.0, gap = 8.0) {
                text("界面偏好设置", height = 28.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "勾选下面的选项，观察摘要如何随之更新。CheckBox 的样式同样来自 style 块。",
                    height = 30.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                for (option in options) {
                    draw(CheckBox(option)) { box ->
                        layout {
                            width = 100.percent
                            height = 38.px
                        }
                        style {
                            background {
                                color = Color.TRANSPARENT
                            }
                            foreground = INK
                            fontSize = 14.px
                        }
                        box.checked = option in chosen.value
                        box.onChange { checked ->
                            chosen.update(if (checked) chosen.value + option else chosen.value - option)
                        }
                    }
                }
            }
            card(height = 110.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("已选择", height = 18.px, size = 11.0, color = BRAND_DEEP, bold = true)
                bareTextArea { summary ->
                    layout {
                        width = 100.percent
                        height = 36.px
                    }
                    style {
                        fontSize = 14.0.px
                        foreground = Color.web("#355f9f")
                    }
                    summary.wrap = true
                    summary.text = if (chosen.value.isEmpty()) {
                        "尚未选择任何选项"
                    } else {
                        chosen.value.joinToString("  ·  ")
                    }
                }.withState(chosen)
            }
        }
    }

    private fun bindFlexBox(pane: TabPane) {
        pane.addPage(
            tabLabel = "▦  FlexBox",
            title = "FlexBox",
            subtitle = "从左到右排列子组件，一行放不下时自动换行。gap 控制间距，justify 控制主轴，align 控制交叉轴。",
        ) {
            card(height = 420.px, pad = 22.0, gap = 10.0) {
                text("自动换行", height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "下面一行放了很多宽度不同的色块，空间不够时它们会自动换到下一行。",
                    height = 30.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                flexFlow(
                    rowHeight = 132.px,
                    justify = Justify.START,
                    align = Align.CENTER,
                    gap = 10.0,
                    fill = Color.web("#f8fafc"),
                    borderColor = LINE,
                ) {
                    val colors = listOf(
                        BRAND,
                        MINT,
                        AMBER,
                        VIOLET,
                        ROSE,
                        Color.web("#0ea5e9"),
                        Color.web("#14b8a6"),
                        Color.web("#6366f1")
                    )
                    val widths = listOf(118.0, 96.0, 128.0, 104.0, 112.0, 92.0, 124.0, 108.0)
                    for (index in colors.indices) {
                        button(
                            label = "Item ${index + 1}",
                            width = widths[index].px,
                            height = 42.px,
                            fill = colors[index],
                            radius = 10.px,
                        )
                    }
                }
                text("justify = START  ·  align = CENTER", height = 18.px, size = 11.0, color = FAINT)
                flexFlow(
                    rowHeight = 84.px,
                    justify = Justify.SPACE_BETWEEN,
                    align = Align.CENTER,
                    gap = 8.0,
                    fill = Color.web("#f8fafc"),
                    borderColor = LINE,
                ) {
                    for (index in 1..4) {
                        button(
                            label = "Chip $index",
                            width = 82.px,
                            height = 36.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                            borderColor = Color.web("#c9d9f7"),
                            radius = 50.percent,
                        )
                    }
                }
                text("justify = SPACE_BETWEEN  ·  剩余空间平均分布在组件之间", height = 18.px, size = 11.0, color = FAINT)
            }
        }
    }

    private fun bindDiv(pane: TabPane) {
        pane.addPage(
            tabLabel = "▭  Div",
            title = "Div",
            subtitle = "一个透明的绝对定位绘制面板。子组件的百分比以 Div 自身为基准，因此可以像画布一样嵌套组合。",
        ) {
            card(height = 440.px, pad = 22.0, gap = 10.0) {
                text("嵌套面板", height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "左侧是父级 Div，右侧是子级 Div。每个子组件的位置与尺寸都相对它所在的面板计算。",
                    height = 30.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                draw(Div()) { stage ->
                    layout {
                        width = 100.percent
                        height = 320.px
                    }
                    style {
                        background {
                            color = Color.web("#e8eef9")
                        }
                        corner = 16.px
                    }
                    stage.layout {
                        draw(Div()) { left ->
                            layout {
                                x = 5.percent
                                y = 8.percent
                                width = 43.percent
                                height = 84.percent
                            }
                            style {
                                background {
                                    color = BRAND
                                }
                                corner = 14.px
                            }
                            left.layout {
                                bareTextArea { headline ->
                                    layout {
                                        x = 10.percent
                                        y = 10.percent
                                        width = 80.percent
                                        height = 34.percent
                                    }
                                    style {
                                        fontSize = 18.0.px
                                        foreground = Color.WHITE
                                    }
                                    headline.bold = true
                                    headline.wrap = true
                                    headline.text = "父级 Div\n43% × 84%"
                                }
                                bareTextArea { note ->
                                    layout {
                                        x = 10.percent
                                        y = 50.percent
                                        width = 80.percent
                                        height = 40.percent
                                    }
                                    style {
                                        fontSize = 12.5.px
                                        foreground = ON_DARK
                                    }
                                    note.wrap = true
                                    note.text = "子组件的位置和尺寸都以这个蓝色面板为基准，而不是整个页面。"
                                }
                            }
                        }
                        draw(Div()) { topRight ->
                            layout {
                                x = 52.percent
                                y = 10.percent
                                width = 43.percent
                                height = 36.percent
                            }
                            style {
                                background {
                                    color = Color.WHITE
                                }
                                corner = 12.px
                                border {
                                    size = 1.px
                                    color = LINE
                                }
                            }
                            topRight.layout {
                                bareTextArea("嵌套卡片") { label ->
                                    layout {
                                        x = 8.percent
                                        y = 18.percent
                                        width = 84.percent
                                        height = 26.percent
                                    }
                                    style {
                                        fontSize = 14.0.px
                                        foreground = INK
                                    }
                                    label.bold = true
                                }
                                bareTextArea("这里是一个白色卡片，同样由 Div 提供圆角与背景。") { label ->
                                    layout {
                                        x = 8.percent
                                        y = 50.percent
                                        width = 84.percent
                                        height = 34.percent
                                    }
                                    style {
                                        fontSize = 11.5.px
                                        foreground = MUTED
                                    }
                                    label.wrap = true
                                }
                            }
                        }
                        draw(Div()) { bottomRight ->
                            layout {
                                x = 52.percent
                                y = 52.percent
                                width = 43.percent
                                height = 40.percent
                            }
                            style {
                                background {
                                    color = Color.web("#dbeafe")
                                }
                                corner = 12.px
                            }
                            bottomRight.layout {
                                bareTextArea("百分比会重新作用域") { label ->
                                    layout {
                                        x = 8.percent
                                        y = 14.percent
                                        width = 84.percent
                                        height = 68.percent
                                    }
                                    style {
                                        fontSize = 12.0.px
                                        foreground = Color.web("#355f9f")
                                    }
                                    label.wrap = true
                                    label.text = "同一个 50% 写在不同的 Div 里，指向的宽度不同。嵌套让局部布局互不干扰。"
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindCard(pane: TabPane) {
        pane.addPage(
            tabLabel = "▢  Card",
            title = "Card",
            subtitle = "带圆角、内边距与描边的内容面板。Card 继承自 FlexBox，内部仍按流式布局排列子组件，百分比也以卡片自身为基准。",
        ) {
            card(height = 214.px, pad = 22.0, gap = 8.0) {
                text("一个卡片，三种用法", height = 28.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "Card 的 h、w、fill、borderColor、pad、gap 与 corner 都可以在构造时指定；它既是一块绘制表面，也是一个 FlexBox。",
                    height = 34.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                draw(
                    Card(
                        height = 90.px,
                        width = 100.percent,
                        fill = BRAND_SOFT,
                        borderColor = null,
                        pad = 14.0,
                        gap = 4.0,
                    )
                ) { surface ->
                    surface.text(
                        content = "直接使用 Card 类",
                        h = 20.px,
                        size = 14.0,
                        color = BRAND_DEEP,
                        bold = true,
                    )
                    surface.text(
                        content = "draw(Card(...)) { card -> card.text(...) } 适合需要自由组合子组件的场景。",
                        h = 34.px,
                        size = 12.0,
                        color = Color.web("#355f9f"),
                        wrap = true,
                    )
                }
            }

            draw(FlexBox()) { row ->
                style {
                    background {
                        color = Color.TRANSPARENT
                    }
                }
                layout {
                    width = 100.percent
                    height = 138.px
                }
                row.gap = 12.px
                row.align = Align.CENTER
                row.layout {
                    card(
                        height = 132.px,
                        width = 32.percent,
                        pad = 16.0,
                        gap = 6.0,
                    ) {
                        text("基础卡片", height = 20.px, size = 15.0, color = INK, bold = true)
                        text(
                            content = "白底、细描边与 16px 圆角，是默认的卡片外观。",
                            height = 40.px,
                            size = 11.5,
                            color = MUTED,
                            wrap = true,
                        )
                        draw(Div()) {
                            layout {
                                width = 36.px
                                height = 4.px
                            }
                            style {
                                background {
                                    color = BRAND
                                }
                                corner = 50.percent
                            }
                        }
                    }
                    card(
                        height = 132.px,
                        width = 32.percent,
                        fill = BRAND_SOFT,
                        borderColor = null,
                        pad = 16.0,
                        gap = 6.0,
                    ) {
                        text("强调卡片", height = 20.px, size = 15.0, color = BRAND_DEEP, bold = true)
                        text(
                            content = "浅蓝底与无描边，适合承载提示、摘要与强调内容。",
                            height = 40.px,
                            size = 11.5,
                            color = Color.web("#355f9f"),
                            wrap = true,
                        )
                        draw(Div()) {
                            layout {
                                width = 36.px
                                height = 4.px
                            }
                            style {
                                background {
                                    color = MINT
                                }
                                corner = 50.percent
                            }
                        }
                    }
                    card(
                        height = 132.px,
                        width = 32.percent,
                        fill = NAVY,
                        borderColor = null,
                        pad = 16.0,
                        gap = 6.0,
                    ) {
                        text("深色卡片", height = 20.px, size = 15.0, color = Color.WHITE, bold = true)
                        text(
                            content = "fill 可以换成任意颜色，文字颜色随之调整即可。",
                            height = 40.px,
                            size = 11.5,
                            color = ON_DARK,
                            wrap = true,
                        )
                        draw(Div()) {
                            layout {
                                width = 36.px
                                height = 4.px
                            }
                            style {
                                background {
                                    color = AMBER
                                }
                                corner = 50.percent
                            }
                        }
                    }
                }
            }

            card(
                height = 112.px,
                fill = Color.web("#f8fafc"),
                borderColor = LINE,
                pad = 18.0,
                gap = 6.0,
            ) {
                text("百分比以卡片自身为基准", height = 18.px, size = 12.0, color = BRAND_DEEP, bold = true)
                text(
                    content = "在 Card 内部写 50.percent，得到的是卡片内容宽度的一半，而不是页面的一半。",
                    height = 38.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
            }
        }
    }

    private fun bindCollapsePane(pane: TabPane) {
        pane.addPage(
            tabLabel = "▥  CollapsePane",
            title = "CollapsePane",
            subtitle = "带可点击标题栏的面板。收起时内容区完全隐藏且不占位，展开时内容区出现并获得标题栏以下的空间。",
        ) {
            card(
                height = 142.px,
                fill = BRAND_SOFT,
                borderColor = null,
                pad = 20.0,
                gap = 6.0,
            ) {
                text("一个标题栏，两种状态", height = 26.px, size = 18.0, color = BRAND_DEEP, bold = true)
                text(
                    content = "点击标题栏即可切换。收起时只保留 40px 高的标题栏；展开后整个面板到达 expandedHeight，内容区只占剩余空间，超出部分纵向滚动。",
                    height = 54.px,
                    size = 12.5,
                    color = Color.web("#355f9f"),
                    wrap = true,
                )
            }

            collapsePane(
                title = "展开状态：标题栏 + 内容区",
                expandedHeight = 224.px,
                initiallyExpanded = true,
                fill = Color.WHITE,
                borderColor = LINE,
                headerForeground = INK,
                bodyPadding = 14.0,
                bodyGap = 8.0,
            ) {
                text(
                    content = "内容区是一个 scrollY = true 的 FlexBox，可以放任意子组件。",
                    height = 40.px,
                    size = 12.5,
                    color = MUTED,
                    wrap = true,
                )
                draw(FlexBox()) { row ->
                    style {
                        background {
                            color = Color.TRANSPARENT
                        }
                    }
                    layout {
                        width = 100.percent
                        height = 36.px
                    }
                    row.gap = 8.px
                    row.align = Align.CENTER
                    row.layout {
                        button(
                            label = "主要操作",
                            width = 104.px,
                            height = 34.px,
                            fill = BRAND,
                            foreground = Color.WHITE,
                        )
                        button(
                            label = "次要操作",
                            width = 104.px,
                            height = 34.px,
                            fill = BRAND_SOFT,
                            foreground = BRAND_DEEP,
                        )
                    }
                }
                text(
                    content = "把 bodyGap 调大、再加几行文字，内容超出后就会出现窄的纵向滚动条。",
                    height = 48.px,
                    size = 12.0,
                    color = FAINT,
                    wrap = true,
                )
            }

            collapsePane(
                title = "默认收起：点击这一行标题栏即可展开或收起",
                expandedHeight = 190.px,
                fill = NAVY,
                borderColor = Color.web("#243354"),
                headerForeground = ON_DARK,
                bodyPadding = 14.0,
                bodyGap = 8.0,
            ) {
                text(
                    content = "收起时，这里的内容完全隐藏，也不会在页面上占据空白。",
                    height = 40.px,
                    size = 12.5,
                    color = ON_DARK,
                    wrap = true,
                )
                text(
                    content = "展开状态可以通过 expanded 属性读取，调用 toggle() 切换；这些状态变化会立刻触发重排。",
                    height = 52.px,
                    size = 12.0,
                    color = Color.web("#a9c7ff"),
                    wrap = true,
                )
            }
        }
    }

    private fun bindComboBox(pane: TabPane) {
        val chosen = State("尚未选择任何选项")
        pane.addPage(
            tabLabel = "≣  ComboBox",
            title = "ComboBox",
            subtitle = "在一个字段里显示当前项，点击时下拉其余项。items 是实时集合，选择与每一行的文字都可以自定义。",
        ) {
            card(height = 412.px, pad = 22.0, gap = 10.0) {
                text("下拉选择", height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "下面演示普通选择、主题选择与禁用状态；添加按钮会直接修改 items，新的项会立刻出现在下拉框里。",
                    height = 30.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                text("尺寸", height = 18.px, size = 12.0, color = MUTED, bold = true)
                val sizeCombo = draw(ComboBox(listOf("Small", "Medium", "Large"))) { combo ->
                    layout {
                        width = 100.percent
                        height = 42.px
                    }
                    style {
                        background {
                            color = Color.web("#f8fafc")
                        }
                        corner = 10.px
                        border {
                            size = 1.px
                            color = LINE
                        }
                        foreground = INK
                        fontSize = 14.px
                    }
                    combo.prompt = "请选择尺寸"
                    combo.onSelectItem { value -> chosen.update("尺寸：$value") }
                }
                text("主题", height = 18.px, size = 12.0, color = MUTED, bold = true)
                val themes = listOf("浅色", "深色", "跟随系统")
                draw(ComboBox(themes)) { combo ->
                    layout {
                        width = 100.percent
                        height = 42.px
                    }
                    style {
                        background {
                            color = Color.web("#f8fafc")
                        }
                        corner = 10.px
                        border {
                            size = 1.px
                            color = LINE
                        }
                        foreground = INK
                        fontSize = 14.px
                    }
                    combo.prompt = "请选择主题"
                    combo.onSelect { index -> chosen.update("主题：${themes[index]}") }
                }
                text("禁用状态", height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(ComboBox(listOf("不可选择"))) { combo ->
                    layout {
                        width = 100.percent
                        height = 42.px
                    }
                    style {
                        background {
                            color = Color.web("#f1f5f9")
                        }
                        corner = 10.px
                        border {
                            size = 1.px
                            color = LINE
                        }
                        foreground = FAINT
                        fontSize = 14.px
                    }
                    combo.prompt = "已禁用"
                    combo.enabled = false
                }
                draw(FlexBox()) { row ->
                    style {
                        background {
                            color = Color.TRANSPARENT
                        }
                    }
                    layout {
                        width = 100.percent
                        height = 36.px
                    }
                    row.gap = 10.px
                    row.align = Align.CENTER
                    row.layout {
                        button(label = "添加自定义尺寸", width = 160.px, height = 36.px) {
                            val name = "自定义 ${sizeCombo.size + 1}"
                            sizeCombo.items.add(name)
                            chosen.update("已添加项目：$name")
                        }
                        button(
                            label = "清空选择",
                            width = 112.px,
                            height = 36.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                            borderColor = Color.web("#c9d9f7"),
                        ) {
                            sizeCombo.clearSelection()
                            chosen.update("已清空尺寸选择")
                        }
                    }
                }
            }
            card(height = 112.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("当前选择", height = 18.px, size = 11.0, color = BRAND_DEEP, bold = true)
                bareTextArea { status ->
                    layout {
                        width = 100.percent
                        height = 36.px
                    }
                    style {
                        fontSize = 14.0.px
                        foreground = Color.web("#355f9f")
                    }
                    status.wrap = true
                    status.text = chosen.value
                }.withState(chosen)
            }
        }
    }

    private fun bindLink(pane: TabPane) {
        val clicked = State("尚未点击任何链接")
        pane.addPage(
            tabLabel = "↗  Link",
            title = "Link",
            subtitle = "表现成超链接的文字：带下划线、悬停为手形光标，点击后在系统浏览器中打开 URL。onClick 可以替换默认的浏览器行为。",
        ) {
            card(height = 372.px, pad = 22.0, gap = 10.0) {
                text("链接的三种形态", height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "默认链接会打开浏览器；修改 foreground 可以改变颜色；注册 onClick 后则只执行自定义逻辑。",
                    height = 30.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                text("打开默认浏览器", height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(Link("Ovatoxin 项目主页", "https://github.com/cao-awa/Ovatoxin")) { link ->
                    layout {
                        width = 100.percent
                        height = 32.px
                    }
                    style {
                        fontSize = 15.px
                    }
                }
                text("覆盖前景色", height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(
                    Link(
                        "阅读组件文档",
                        "https://github.com/cao-awa/Ovatoxin/blob/main/docs/component/README_zh.md",
                    )
                ) {
                    layout {
                        width = 100.percent
                        height = 32.px
                    }
                    style {
                        fontSize = 15.px
                        foreground = MINT
                    }
                }
                text("自定义点击行为", height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(Link("点我更新状态", "")) { link ->
                    layout {
                        width = 100.percent
                        height = 32.px
                    }
                    style {
                        fontSize = 15.px
                        foreground = VIOLET
                    }
                    link.onClick { clicked.update("自定义链接被点击了 ✓") }
                }
                text(
                    content = "链接的 url 为空时，点击不会打开任何东西；注册 onClick 后，链接只执行处理器，不再打开浏览器。",
                    height = 30.px,
                    size = 12.5,
                    color = MUTED,
                    wrap = true,
                )
            }
            card(height = 112.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("点击反馈", height = 18.px, size = 11.0, color = BRAND_DEEP, bold = true)
                bareTextArea { status ->
                    layout {
                        width = 100.percent
                        height = 36.px
                    }
                    style {
                        fontSize = 14.0.px
                        foreground = Color.web("#355f9f")
                    }
                    status.wrap = true
                    status.text = clicked.value
                }.withState(clicked)
            }
        }
    }

    private fun bindToast(pane: TabPane, toast: Toast) {
        val last = State("还没有弹出 Toast。点击上面的按钮试试。")
        pane.addPage(
            tabLabel = "◍  Toast",
            title = "Toast",
            subtitle = "自动消失的浮动提示。它画在页面最上层，按信息、成功、警告、错误四种语气着色，还能居中显示、绘制任意组件，或用遮罩让页面变灰。",
        ) {
            card(height = 430.px, pad = 22.0, gap = 10.0) {
                text("四种语气", height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "点击按钮调用 toast.show(...)。消息默认从窗口底部淡入，停留 2.6 秒后再自动淡出；再次触发会替换当前卡片并重新计时。",
                    height = 38.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                text("提示类型", height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(FlexBox()) { row ->
                    style {
                        background {
                            color = Color.TRANSPARENT
                        }
                    }
                    layout {
                        width = 100.percent
                        height = 40.px
                    }
                    row.gap = 10.px
                    row.align = Align.CENTER
                    row.layout {
                        button(
                            label = "信息提示",
                            width = 112.px,
                            height = 36.px,
                            fill = ToastTone.INFO.background,
                        ) {
                            toast.show("普通信息：操作已完成。", tone = ToastTone.INFO)
                            last.update("信息提示 · 底部居中 · 2.6 秒")
                        }
                        button(
                            label = "成功提示",
                            width = 112.px,
                            height = 36.px,
                            fill = ToastTone.SUCCESS.background,
                        ) {
                            toast.show("保存成功：设置已经更新。", tone = ToastTone.SUCCESS)
                            last.update("成功提示 · 底部居中 · 2.6 秒")
                        }
                        button(
                            label = "警告提示",
                            width = 112.px,
                            height = 36.px,
                            fill = ToastTone.WARNING.background,
                        ) {
                            toast.show("请注意：当前操作不可撤销。", tone = ToastTone.WARNING)
                            last.update("警告提示 · 底部居中 · 2.6 秒")
                        }
                        button(
                            label = "错误提示",
                            width = 112.px,
                            height = 36.px,
                            fill = ToastTone.ERROR.background,
                        ) {
                            toast.show("请求失败：请检查网络连接。", tone = ToastTone.ERROR)
                            last.update("错误提示 · 底部居中 · 2.6 秒")
                        }
                    }
                }
                text("位置与时长", height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(FlexBox()) { row ->
                    style {
                        background {
                            color = Color.TRANSPARENT
                        }
                    }
                    layout {
                        width = 100.percent
                        height = 40.px
                    }
                    row.gap = 10.px
                    row.align = Align.CENTER
                    row.layout {
                        button(
                            label = "顶部居中",
                            width = 104.px,
                            height = 36.px,
                            fill = BRAND_DEEP,
                        ) {
                            toast.show(
                                message = "这条提示出现在窗口顶部。",
                                position = ToastPosition.TOP_CENTER,
                            )
                            last.update("自定义位置 · 顶部居中")
                        }
                        button(
                            label = "居中提示",
                            width = 104.px,
                            height = 36.px,
                            fill = Color.web("#0f766e"),
                        ) {
                            toast.show(
                                message = "这条提示出现在窗口正中央。",
                                position = ToastPosition.CENTER,
                            )
                            last.update("自定义位置 · 居中")
                        }
                        button(
                            label = "长消息 · 3.5 秒",
                            width = 156.px,
                            height = 36.px,
                            fill = VIOLET,
                        ) {
                            toast.show(
                                message = "这是一条比较长的 Toast 消息，卡片会限制最大宽度并自动换行，长文本也不会撑破窗口。",
                                duration = 3500.milliseconds,
                                tone = ToastTone.INFO,
                            )
                            last.update("长消息 · 底部居中 · 3.5 秒")
                        }
                    }
                }
                text("组件内容与遮罩", height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(FlexBox()) { row ->
                    style {
                        background {
                            color = Color.TRANSPARENT
                        }
                    }
                    layout {
                        width = 100.percent
                        height = 40.px
                    }
                    row.gap = 10.px
                    row.align = Align.CENTER
                    row.layout {
                        button(
                            label = "组件内容",
                            width = 112.px,
                            height = 36.px,
                            fill = MINT,
                        ) {
                            toast.show(
                                duration = 5.seconds,
                                position = ToastPosition.CENTER,
                                tone = ToastTone.SUCCESS,
                            ) {
                                text("组件内容 Toast", height = 24.px, size = 17.0, color = Color.WHITE, bold = true)
                                text(
                                    content = "卡片里可以放任意组件：说明文字、按钮、输入框都可以用熟悉的 draw DSL 画出来。",
                                    height = 36.px,
                                    size = 12.5,
                                    color = Color.web("#ecfdf5"),
                                    wrap = true,
                                )
                                button(
                                    label = "知道了",
                                    width = 112.px,
                                    height = 34.px,
                                    fill = Color.WHITE,
                                    foreground = Color.web("#047857"),
                                ) {
                                    toast.dismiss()
                                }
                            }
                            last.update("组件内容 · 居中 · 5 秒")
                        }
                        button(
                            label = "遮罩变灰",
                            width = 112.px,
                            height = 36.px,
                            fill = ROSE,
                        ) {
                            toast.show(
                                duration = 5.seconds,
                                position = ToastPosition.CENTER,
                                tone = ToastTone.INFO,
                                dimOutside = true,
                            ) {
                                text("遮罩提示", height = 24.px, size = 17.0, color = Color.WHITE, bold = true)
                                text(
                                    content = "Toast 之外的区域会变灰并暂时无法点击，卡片内的按钮仍然可以正常操作。",
                                    height = 36.px,
                                    size = 12.5,
                                    color = ON_DARK,
                                    wrap = true,
                                )
                                button(
                                    label = "我知道了",
                                    width = 124.px,
                                    height = 34.px,
                                    fill = Color.WHITE,
                                    foreground = NAVY,
                                ) {
                                    toast.dismiss()
                                }
                            }
                            last.update("遮罩变灰 · 居中 · 5 秒")
                        }
                    }
                }
                text(
                    content = "普通消息的 Toast 浮层仍是鼠标透明的；带组件内容时卡片本身可操作，开启 dimOutside 后才会用遮罩压暗并挡住页面其余部分。",
                    height = 38.px,
                    size = 12.5,
                    color = MUTED,
                    wrap = true,
                )
            }
            card(height = 112.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("最近一次触发", height = 18.px, size = 11.0, color = BRAND_DEEP, bold = true)
                bareTextArea { status ->
                    layout {
                        width = 100.percent
                        height = 36.px
                    }
                    style {
                        fontSize = 14.0.px
                        foreground = Color.web("#355f9f")
                    }
                    status.wrap = true
                    status.text = last.value
                }.withState(last)
            }
        }
    }
}
