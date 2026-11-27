package com.github.kusa233.ovatoxin.samples.full

import com.github.kusa233.ovatoxin.component.SurfaceScope
import com.github.kusa233.ovatoxin.i18n.I18N
import com.github.kusa233.ovatoxin.i18n.translatable
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
import com.github.kusa233.ovatoxin.widget.media.VideoPane
import com.github.kusa233.ovatoxin.widget.progress.ProgressBar
import com.github.kusa233.ovatoxin.widget.progress.progressBar
import com.github.kusa233.ovatoxin.widget.text.Link
import com.github.kusa233.ovatoxin.widget.text.bareTextArea
import com.github.kusa233.ovatoxin.window.Frame
import javafx.application.Platform
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import kotlin.time.Duration
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

private data class Language(val code: String, val label: String)

private val LANGUAGES = listOf(
    Language("zh-cn", "简体中文"),
    Language("en-us", "English"),
    Language("ja-jp", "日本語"),
)

/**
 * Registers every translation used by the I18N sample. The first [I18N.lang] call also fixes the
 * initial language (Simplified Chinese), so calling this once before the page is built is enough.
 */
private fun registerI18nTranslations() {
    I18N.lang("zh-cn", "i18n.headline", "多语言（I18N）示例")
    I18N.lang("zh-cn", "i18n.intro", "Ovatoxin 通过 I18N 注册表集中管理多语言文本。把 \"key\".translatable 作为文本内容后，运行时切换语言即可让界面文字自动刷新，无需重新创建任何控件。")
    I18N.lang("zh-cn", "i18n.label.language", "界面语言")
    I18N.lang("zh-cn", "i18n.greeting", "你好，世界！")
    I18N.lang("zh-cn", "i18n.button.switch", "切换到下一语言")
    I18N.lang("zh-cn", "i18n.compare.title", "普通字符串与 Translatable 的区别")
    I18N.lang("zh-cn", "i18n.compare.literal", "普通字符串：原样显示键名，永不翻译。")
    I18N.lang("zh-cn", "i18n.compare.translatable", "Translatable：显示当前语言的翻译结果。")
    I18N.lang("zh-cn", "i18n.compare.note", "上面两行使用同一个键 i18n.greeting：普通 String 展示键名，Translatable 展示翻译；切换语言时只有 Translatable 会变化。")

    I18N.lang("en-us", "i18n.headline", "Multilingual (I18N) sample")
    I18N.lang("en-us", "i18n.intro", "Ovatoxin centralises multilingual text in the I18N registry. Once a text is given a \"key\".translatable, switching the language at runtime refreshes it automatically — no widget has to be rebuilt.")
    I18N.lang("en-us", "i18n.label.language", "Interface language")
    I18N.lang("en-us", "i18n.greeting", "Hello, world!")
    I18N.lang("en-us", "i18n.button.switch", "Switch to next language")
    I18N.lang("en-us", "i18n.compare.title", "Plain String vs. Translatable")
    I18N.lang("en-us", "i18n.compare.literal", "Plain String: shows the key verbatim, never translated.")
    I18N.lang("en-us", "i18n.compare.translatable", "Translatable: shows the translation for the current language.")
    I18N.lang("en-us", "i18n.compare.note", "Both lines above use the same key i18n.greeting: the plain String shows the key, the Translatable shows the translation; only the Translatable changes when the language switches.")

    I18N.lang("ja-jp", "i18n.headline", "多言語（I18N）サンプル")
    I18N.lang("ja-jp", "i18n.intro", "Ovatoxin は I18N レジストリでテキストの多言語化を管理します。\"key\".translatable を指定しておけば、実行中に言語を切り替えるだけで表示が自動更新され、ウィジェットを作り直す必要はありません。")
    I18N.lang("ja-jp", "i18n.label.language", "表示言語")
    I18N.lang("ja-jp", "i18n.greeting", "こんにちは、世界！")
    I18N.lang("ja-jp", "i18n.button.switch", "次の言語に切り替え")
    I18N.lang("ja-jp", "i18n.compare.title", "通常の String と Translatable の違い")
    I18N.lang("ja-jp", "i18n.compare.literal", "通常の文字列：キーをそのまま表示し、翻訳されません。")
    I18N.lang("ja-jp", "i18n.compare.translatable", "Translatable：現在の言語の翻訳を表示します。")
    I18N.lang("ja-jp", "i18n.compare.note", "上の 2 行は同じキー i18n.greeting を使っています。通常の String はキーを、Translatable は翻訳を表示し、言語を切り替えると Translatable だけが変化します。")
}

private val FEATURES = listOf(
    Feature("▤", "TextArea", "features.description.1", 1, BRAND),
    Feature("⌨", "TextField", "features.description.2", 2, MINT),
    Feature("⬢", "Button", "features.description.3", 3, AMBER),
    Feature("◐", "ProgressBar", "features.description.4", 4, VIOLET),
    Feature("☰", "ListView", "features.description.5", 5, ROSE),
    Feature("☑", "CheckBox", "features.description.6", 6, Color.web("#0ea5e9")),
    Feature("▦", "FlexBox", "features.description.7", 7, Color.web("#14b8a6")),
    Feature("▭", "Div", "features.description.8", 8, Color.web("#6366f1")),
    Feature("▢", "Card", "features.description.9", 9, Color.web("#64748b")),
    Feature("▥", "CollapsePane", "features.description.10", 10, Color.web("#0ea5e9")),
    Feature("≣", "ComboBox", "features.description.11", 11, Color.web("#d946ef")),
    Feature("↗", "Link", "features.description.12", 12, Color.web("#2563eb")),
    Feature("◍", "Toast", "features.description.13", 13, Color.web("#0f766e")),
    Feature("文", "I18N", "features.description.14", 14, Color.web("#0ea5e9")),
    Feature("▶", "VideoPane", "features.description.15", 15, Color.web("#ef4444")),
)

private val ENTRIES = listOf(
    ListEntry("◈", "Ovatoxin", "entries.detail.1"),
    ListEntry("▤", "TextArea", "entries.detail.2"),
    ListEntry("⌨", "TextField", "entries.detail.3"),
    ListEntry("⬢", "Button", "entries.detail.4"),
    ListEntry("◐", "ProgressBar", "entries.detail.5"),
    ListEntry("☰", "ListView", "entries.detail.6"),
    ListEntry("☑", "CheckBox", "entries.detail.7"),
    ListEntry("▦", "FlexBox", "entries.detail.8"),
    ListEntry("▭", "Div", "entries.detail.9"),
    ListEntry("▢", "Card", "entries.detail.10"),
    ListEntry("▥", "CollapsePane", "entries.detail.11"),
    ListEntry("≣", "ComboBox", "entries.detail.12"),
    ListEntry("↗", "Link", "entries.detail.13"),
    ListEntry("◍", "Toast", "entries.detail.14"),
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
    "I18N 多语言" to """
        // 先注册各语言文本（首个 lang 调用同时确定初始语言）
        I18N.lang("zh-cn", "greeting", "你好，世界！")
        I18N.lang("en-us", "greeting", "Hello, world!")

        // 语言选择器：切换后所有 Translatable 文本都会自动刷新
        val combo = draw(ComboBox(LANGUAGES) { it.label }) { c ->
            layout {
                width = 100.percent
                height = 44.px
            }
            c.onSelectItem { language -> I18N.currentLang = language.code }
        }

        // 用 "key".translatable 作为内容即可自动翻译
        text("greeting".translatable, height = 48.px, size = 24.0, bold = true)
    """,
    "VideoPane" to """
        val video = draw(VideoPane("classpath:/media/sample.mp4")) { v ->
            layout {
                width = 100.percent
                height = 300.px
            }
            style {
                corner = 12.px
            }
            v.loop = true
            v.volume = 0.8
        }

        video.play()
        video.seekTo(video.position + 5.seconds)
        video.onError { e -> println(e?.message) }
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
        // Register every translation up front: keys are semantic English names now, so the registry
        // must exist before any page builds its widgets (otherwise the raw keys would be rendered).
        registerI18nTranslations()
        registerAllTranslations()
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
                bindI18n(pane)
                bindVideoPane(pane)
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
    private val relocalizers = mutableListOf<() -> Unit>()

    private fun relocalize(block: () -> Unit) {
        relocalizers += block
        block()
    }

    private fun applyLanguage() {
        relocalizers.forEach { it() }
    }

    private fun TabPane.addPage(
        tabLabel: String,
        title: String,
        subtitle: String,
        body: SurfaceScope<FlexBox>.() -> Unit,
    ): Button {
        val tab = add(Button(tabLabel))
        tab.text(tabLabel.translatable)
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
                    content = title.translatable,
                    height = 40.px,
                    size = 29.0,
                    color = INK,
                    bold = true,
                )
                text(
                    content = subtitle.translatable,
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
        val samplePane = collapsePane(
            title = I18N.content("code.title"),
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
        relocalize { samplePane.title = I18N.content("code.title") }
    }

    private fun bindMain(pane: TabPane) {
        pane.addPage(
            tabLabel = "◆  Ovatoxin",
            title = "overview.title",
            subtitle = "overview.subtitle",
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
                    content = "overview.body.1".translatable,
                    height = 34.px,
                    size = 26.0,
                    color = Color.WHITE,
                    bold = true,
                )
                text(
                    content = "overview.body.2".translatable,
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
                            label = "overview.label.1".translatable,
                            width = 130.px,
                            height = 34.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                        ) {
                            pane.select(1)
                        }
                        button(
                            label = "overview.label.2".translatable,
                            width = 130.px,
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
                        content = feature.description.translatable,
                        height = 36.px,
                        size = 11.5,
                        color = MUTED,
                        wrap = true,
                    )
                    text(
                        content = "overview.body.3".translatable,
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
            subtitle = "textarea.subtitle",
        ) {
            card(height = 430.px, pad = 22.0, gap = 12.0) {
                text("textarea.text".translatable, height = 28.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "textarea.body.1".translatable,
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
                    content = "textarea.intro".translatable,
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
                        content = "textarea.body.2".translatable,
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
            subtitle = "textfield.subtitle",
        ) {
            val live = State("textfield.state.1")
            card(height = 430.px, pad = 22.0, gap = 10.0) {
                text("textfield.text.1".translatable, height = 28.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "textfield.body".translatable,
                    height = 34.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                text("textfield.text.2".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
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
                    field.hint = I18N.content("textfield.hint")
                    relocalize { field.hint = I18N.content("textfield.hint") }
                }
                text("textfield.text.3".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
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
                    live.update(I18N.content("textfield.prefix.name") + nameField.text() + "    " + I18N.content("textfield.prefix.email") + mailField.text())
                }
                mailField.onChange {
                    live.update(I18N.content("textfield.prefix.name") + nameField.text() + "    " + I18N.content("textfield.prefix.email") + mailField.text())
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
                        button(label = "textfield.label.1".translatable, width = 96.px, height = 38.px) {
                            live.update(I18N.content("textfield.prefix.submitted") + nameField.text() + " · " + mailField.text())
                        }
                        button(
                            label = "textfield.label.2".translatable,
                            width = 96.px,
                            height = 38.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                            borderColor = Color.web("#c9d9f7"),
                        ) {
                            nameField.text("")
                            mailField.text("")
                            live.update("textfield.state.1")
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
                    status.text(live.value.translatable)
                }.withState(live)
            }
        }
    }

    private fun bindButton(pane: TabPane) {
        pane.addPage(
            tabLabel = "⬢  Button",
            title = "Button",
            subtitle = "button.subtitle",
        ) {
            val feedback = State("button.state.1")
            card(height = 250.px, pad = 22.0, gap = 10.0) {
                text("button.text.1".translatable, height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "button.body.1".translatable,
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
                        button(label = "button.label.1".translatable, width = 116.px, height = 42.px) {
                            feedback.update("button.state.2")
                        }
                        button(
                            label = "button.label.2".translatable,
                            width = 116.px,
                            height = 42.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                            borderColor = Color.web("#b9d1fb"),
                        ) {
                            feedback.update("button.state.3")
                        }
                        button(
                            label = "button.label.3".translatable,
                            width = 116.px,
                            height = 42.px,
                            fill = MINT,
                            radius = 50.percent,
                        ) {
                            feedback.update("button.state.4")
                        }
                        button(
                            label = "button.label.4".translatable,
                            width = 116.px,
                            height = 42.px,
                            fill = Color.web("#e9edf5"),
                            foreground = Color.web("#a2acbd"),
                        ) {
                            feedback.update("button.state.5")
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
                    status.text(feedback.value.translatable)
                }.withState(feedback)
            }
            card(height = 126.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("button.text.2".translatable, height = 18.px, size = 12.0, color = BRAND_DEEP, bold = true)
                text(
                    content = "button.body.2".translatable,
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
            subtitle = "progress.subtitle",
        ) {
            card(height = 430.px, pad = 22.0, gap = 10.0) {
                text("progress.text.1".translatable, height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "progress.body.1".translatable,
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
                        text("progress.text.2".translatable, width = 70.percent, height = 24.px, size = 13.0, color = INK)
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
                            percentLabel.text("${progress.value.value.toInt()}%")
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
                text("progress.text.3".translatable, height = 24.px, size = 15.0, color = INK, bold = true)
                progressBar(h = 14.px, value = 35.percent, tone = MINT, track = TRACK)
                progressBar(h = 14.px, value = 62.percent, tone = AMBER, track = TRACK)
                progressBar(h = 14.px, value = 88.percent, tone = VIOLET, track = TRACK)
                text(
                    content = "progress.body.2".translatable,
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
            subtitle = "listview.subtitle",
        ) {
            val selected = State<ListEntry?>(ENTRIES.first())
            card(height = 440.px, width = 57.percent, pad = 20.0, gap = 8.0) {
                text("listview.text.1".translatable, height = 24.px, size = 17.0, color = INK, bold = true)
                text("listview.text.2".translatable, height = 18.px, size = 11.5, color = FAINT)
                val list =
                    draw(ListView(ENTRIES) { entry -> "${entry.glyph}   ${entry.name}      ·   ${I18N.content(entry.detail)}" }) { view ->
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
                text("listview.text.3".translatable, height = 20.px, size = 11.0, color = FAINT, bold = true)
                bareTextArea { icon ->
                    layout {
                        width = 100.percent
                        height = 38.px
                    }
                    style {
                        fontSize = 26.0.px
                        foreground = BRAND
                    }
                    icon.text(selected.value?.glyph ?: "◌")
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
                    name.text(selected.value?.name?.translatable ?: "listview.text.4".translatable)
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
                    detail.text(selected.value?.detail?.translatable ?: "listview.text.5".translatable)
                }.withState(selected)
                text(
                    content = "listview.body".translatable,
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
            subtitle = "checkbox.subtitle",
        ) {
            val chosen = State(setOf("checkbox.item.1"))
            val options = listOf("checkbox.item.1", "checkbox.item.2", "checkbox.item.3", "checkbox.item.4")
            card(height = 300.px, pad = 22.0, gap = 8.0) {
                text("checkbox.text.1".translatable, height = 28.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "checkbox.body".translatable,
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
                        box.text(option.translatable)
                        box.checked = option in chosen.value
                        box.onChange { checked ->
                            chosen.update(if (checked) chosen.value + option else chosen.value - option)
                        }
                    }
                }
            }
            card(height = 110.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("checkbox.text.2".translatable, height = 18.px, size = 11.0, color = BRAND_DEEP, bold = true)
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
                    summary.text(
                        if (chosen.value.isEmpty()) {
                            "checkbox.text.3".translatable
                        } else {
                            chosen.value.joinToString("  ·  ") { I18N.content(it) }.translatable
                        }
                    )
                }.withState(chosen)
            }
        }
    }

    private fun bindFlexBox(pane: TabPane) {
        pane.addPage(
            tabLabel = "▦  FlexBox",
            title = "FlexBox",
            subtitle = "flexbox.subtitle",
        ) {
            card(height = 420.px, pad = 22.0, gap = 10.0) {
                text("flexbox.text.1".translatable, height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "flexbox.body".translatable,
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
                text("flexbox.text.2".translatable, height = 18.px, size = 11.0, color = FAINT)
            }
        }
    }

    private fun bindDiv(pane: TabPane) {
        pane.addPage(
            tabLabel = "▭  Div",
            title = "Div",
            subtitle = "div.subtitle",
        ) {
            card(height = 440.px, pad = 22.0, gap = 10.0) {
                text("div.text.1".translatable, height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "div.body".translatable,
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
                                    headline.text("div.text.2".translatable)
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
                                    note.text("div.text.3".translatable)
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
                                bareTextArea("div.text.4".translatable) { label ->
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
                                bareTextArea("div.text.5".translatable) { label ->
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
                                bareTextArea("div.text.6".translatable) { label ->
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
                                    label.text("div.text.7".translatable)
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
            subtitle = "card.subtitle",
        ) {
            card(height = 214.px, pad = 22.0, gap = 8.0) {
                text("card.text.1".translatable, height = 28.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "card.body.1".translatable,
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
                        content = "card.body.2".translatable,
                        h = 20.px,
                        size = 14.0,
                        color = BRAND_DEEP,
                        bold = true,
                    )
                    surface.text(
                        content = "card.body.3".translatable,
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
                        text("card.text.2".translatable, height = 20.px, size = 15.0, color = INK, bold = true)
                        text(
                            content = "card.body.4".translatable,
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
                        text("card.text.3".translatable, height = 20.px, size = 15.0, color = BRAND_DEEP, bold = true)
                        text(
                            content = "card.body.5".translatable,
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
                        text("card.text.4".translatable, height = 20.px, size = 15.0, color = Color.WHITE, bold = true)
                        text(
                            content = "card.body.6".translatable,
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
                text("card.text.5".translatable, height = 18.px, size = 12.0, color = BRAND_DEEP, bold = true)
                text(
                    content = "card.body.7".translatable,
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
            subtitle = "collapse.subtitle",
        ) {
            card(
                height = 142.px,
                fill = BRAND_SOFT,
                borderColor = null,
                pad = 20.0,
                gap = 6.0,
            ) {
                text("collapse.text".translatable, height = 26.px, size = 18.0, color = BRAND_DEEP, bold = true)
                text(
                    content = "collapse.body.1".translatable,
                    height = 54.px,
                    size = 12.5,
                    color = Color.web("#355f9f"),
                    wrap = true,
                )
            }

            val openPane = collapsePane(
                title = I18N.content("collapse.title.1"),
                expandedHeight = 224.px,
                initiallyExpanded = true,
                fill = Color.WHITE,
                borderColor = LINE,
                headerForeground = INK,
                bodyPadding = 14.0,
                bodyGap = 8.0,
            ) {
                text(
                    content = "collapse.body.2".translatable,
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
                            label = "collapse.label.1".translatable,
                            width = 104.px,
                            height = 34.px,
                            fill = BRAND,
                            foreground = Color.WHITE,
                        )
                        button(
                            label = "collapse.label.2".translatable,
                            width = 104.px,
                            height = 34.px,
                            fill = BRAND_SOFT,
                            foreground = BRAND_DEEP,
                        )
                    }
                }
                text(
                    content = "collapse.body.3".translatable,
                    height = 48.px,
                    size = 12.0,
                    color = FAINT,
                    wrap = true,
                )
            }
            relocalize { openPane.title = I18N.content("collapse.title.1") }

            val closedPane = collapsePane(
                title = I18N.content("collapse.title.2"),
                expandedHeight = 190.px,
                fill = NAVY,
                borderColor = Color.web("#243354"),
                headerForeground = ON_DARK,
                bodyPadding = 14.0,
                bodyGap = 8.0,
            ) {
                text(
                    content = "collapse.body.4".translatable,
                    height = 40.px,
                    size = 12.5,
                    color = ON_DARK,
                    wrap = true,
                )
                text(
                    content = "collapse.body.5".translatable,
                    height = 52.px,
                    size = 12.0,
                    color = Color.web("#a9c7ff"),
                    wrap = true,
                )
            }
            relocalize { closedPane.title = I18N.content("collapse.title.2") }
        }
    }

    private fun bindComboBox(pane: TabPane) {
        val chosen = State("checkbox.text.3")
        pane.addPage(
            tabLabel = "≣  ComboBox",
            title = "ComboBox",
            subtitle = "combobox.subtitle",
        ) {
            card(height = 412.px, pad = 22.0, gap = 10.0) {
                text("combobox.text.1".translatable, height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "combobox.body".translatable,
                    height = 30.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                text("combobox.text.2".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
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
                    combo.prompt = I18N.content("combobox.prompt.1")
                    relocalize { combo.prompt = I18N.content("combobox.prompt.1") }
                    combo.onSelectItem { value -> chosen.update(I18N.content("combobox.prefix.size") + value) }
                }
                text("combobox.text.3".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
                val themes = listOf("combobox.item.1", "combobox.item.2", "combobox.item.3")
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
                    combo.prompt = I18N.content("combobox.prompt.2")
                    relocalize { combo.prompt = I18N.content("combobox.prompt.2") }
                    combo.onSelect { index -> chosen.update(I18N.content("combobox.prefix.theme") + I18N.content(themes[index])) }
                }
                text("button.label.4".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(ComboBox(listOf("combobox.item.4"))) { combo ->
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
                    combo.prompt = I18N.content("combobox.prompt.3")
                    relocalize { combo.prompt = I18N.content("combobox.prompt.3") }
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
                        button(label = "combobox.label.1".translatable, width = 160.px, height = 36.px) {
                            val name = I18N.content("combobox.custom_prefix") + (sizeCombo.size + 1)
                            sizeCombo.items.add(name)
                            chosen.update(I18N.content("combobox.prefix.added") + name)
                        }
                        button(
                            label = "combobox.label.2".translatable,
                            width = 112.px,
                            height = 36.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                            borderColor = Color.web("#c9d9f7"),
                        ) {
                            sizeCombo.clearSelection()
                            chosen.update("combobox.state.4")
                        }
                    }
                }
            }
            card(height = 112.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("combobox.text.4".translatable, height = 18.px, size = 11.0, color = BRAND_DEEP, bold = true)
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
                    status.text(chosen.value.translatable)
                }.withState(chosen)
            }
        }
    }

    private fun bindLink(pane: TabPane) {
        val clicked = State("link.state.1")
        pane.addPage(
            tabLabel = "↗  Link",
            title = "Link",
            subtitle = "link.subtitle",
        ) {
            card(height = 372.px, pad = 22.0, gap = 10.0) {
                text("link.text.1".translatable, height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "link.body.1".translatable,
                    height = 30.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                text("link.text.2".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(Link("link.text.3", "https://github.com/cao-awa/Ovatoxin")) { link ->
                    link.text("link.text.3".translatable)
                    layout {
                        width = 100.percent
                        height = 32.px
                    }
                    style {
                        fontSize = 15.px
                    }
                }
                text("link.text.4".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
                val docLink = draw(
                    Link(
                        "link.text.5",
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
                docLink.text("link.text.5".translatable)
                text("link.text.6".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
                draw(Link("link.text.7", "")) { link ->
                    link.text("link.text.7".translatable)
                    layout {
                        width = 100.percent
                        height = 32.px
                    }
                    style {
                        fontSize = 15.px
                        foreground = VIOLET
                    }
                    link.onClick { clicked.update("link.state.2") }
                }
                text(
                    content = "link.body.2".translatable,
                    height = 30.px,
                    size = 12.5,
                    color = MUTED,
                    wrap = true,
                )
            }
            card(height = 112.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("link.text.8".translatable, height = 18.px, size = 11.0, color = BRAND_DEEP, bold = true)
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
                    status.text(clicked.value.translatable)
                }.withState(clicked)
            }
        }
    }

    private fun bindToast(pane: TabPane, toast: Toast) {
        val last = State("toast.state.1")
        pane.addPage(
            tabLabel = "◍  Toast",
            title = "Toast",
            subtitle = "toast.subtitle",
        ) {
            card(height = 430.px, pad = 22.0, gap = 10.0) {
                text("toast.text.1".translatable, height = 26.px, size = 19.0, color = INK, bold = true)
                text(
                    content = "toast.body.1".translatable,
                    height = 38.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
                text("toast.text.2".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
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
                            label = "toast.label.1".translatable,
                            width = 112.px,
                            height = 36.px,
                            fill = ToastTone.INFO.background,
                        ) {
                            toast.show(I18N.content("toast.text.3"), tone = ToastTone.INFO)
                            last.update("toast.state.2")
                        }
                        button(
                            label = "toast.label.2".translatable,
                            width = 112.px,
                            height = 36.px,
                            fill = ToastTone.SUCCESS.background,
                        ) {
                            toast.show(I18N.content("toast.text.4"), tone = ToastTone.SUCCESS)
                            last.update("toast.state.3")
                        }
                        button(
                            label = "toast.label.3".translatable,
                            width = 112.px,
                            height = 36.px,
                            fill = ToastTone.WARNING.background,
                        ) {
                            toast.show(I18N.content("toast.text.5"), tone = ToastTone.WARNING)
                            last.update("toast.state.4")
                        }
                        button(
                            label = "toast.label.4".translatable,
                            width = 112.px,
                            height = 36.px,
                            fill = ToastTone.ERROR.background,
                        ) {
                            toast.show(I18N.content("toast.text.6"), tone = ToastTone.ERROR)
                            last.update("toast.state.5")
                        }
                    }
                }
                text("toast.text.7".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
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
                            label = "toast.label.5".translatable,
                            width = 104.px,
                            height = 36.px,
                            fill = BRAND_DEEP,
                        ) {
                            toast.show(
                                message = I18N.content("toast.text.8"),
                                position = ToastPosition.TOP_CENTER,
                            )
                            last.update("toast.state.6")
                        }
                        button(
                            label = "toast.label.6".translatable,
                            width = 104.px,
                            height = 36.px,
                            fill = Color.web("#0f766e"),
                        ) {
                            toast.show(
                                message = I18N.content("toast.text.9"),
                                position = ToastPosition.CENTER,
                            )
                            last.update("toast.state.7")
                        }
                        button(
                            label = "toast.label.7".translatable,
                            width = 156.px,
                            height = 36.px,
                            fill = VIOLET,
                        ) {
                            toast.show(
                                message = I18N.content("toast.text.10"),
                                duration = 3500.milliseconds,
                                tone = ToastTone.INFO,
                            )
                            last.update("toast.state.8")
                        }
                    }
                }
                text("toast.text.11".translatable, height = 18.px, size = 12.0, color = MUTED, bold = true)
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
                            label = "toast.label.8".translatable,
                            width = 112.px,
                            height = 36.px,
                            fill = MINT,
                        ) {
                            toast.show(
                                duration = 5.seconds,
                                position = ToastPosition.CENTER,
                                tone = ToastTone.SUCCESS,
                            ) {
                                text("toast.text.12".translatable, height = 24.px, size = 17.0, color = Color.WHITE, bold = true)
                                text(
                                    content = "toast.body.2".translatable,
                                    height = 36.px,
                                    size = 12.5,
                                    color = Color.web("#ecfdf5"),
                                    wrap = true,
                                )
                                button(
                                    label = "toast.label.9".translatable,
                                    width = 112.px,
                                    height = 34.px,
                                    fill = Color.WHITE,
                                    foreground = Color.web("#047857"),
                                ) {
                                    toast.dismiss()
                                }
                            }
                            last.update("toast.state.9")
                        }
                        button(
                            label = "toast.label.10".translatable,
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
                                text("toast.text.13".translatable, height = 24.px, size = 17.0, color = Color.WHITE, bold = true)
                                text(
                                    content = "toast.body.3".translatable,
                                    height = 36.px,
                                    size = 12.5,
                                    color = ON_DARK,
                                    wrap = true,
                                )
                                button(
                                    label = "toast.label.11".translatable,
                                    width = 124.px,
                                    height = 34.px,
                                    fill = Color.WHITE,
                                    foreground = NAVY,
                                ) {
                                    toast.dismiss()
                                }
                            }
                            last.update("toast.state.10")
                        }
                    }
                }
                text(
                    content = "toast.body.4".translatable,
                    height = 38.px,
                    size = 12.5,
                    color = MUTED,
                    wrap = true,
                )
            }
            card(height = 112.px, fill = BRAND_SOFT, borderColor = null, pad = 20.0, gap = 6.0) {
                text("toast.text.14".translatable, height = 18.px, size = 11.0, color = BRAND_DEEP, bold = true)
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
                    status.text(last.value.translatable)
                }.withState(last)
            }
        }
    }

    private fun bindI18n(pane: TabPane) {
        pane.addPage(
            tabLabel = "i18n.tab",
            title = "i18n.title",
            subtitle = "i18n.subtitle",
        ) {
            card(height = 172.px, pad = 22.0, gap = 8.0) {
                text(
                    content = "i18n.headline".translatable,
                    height = 34.px,
                    size = 24.0,
                    color = INK,
                    bold = true,
                )
                text(
                    content = "i18n.intro".translatable,
                    height = 96.px,
                    size = 13.0,
                    color = MUTED,
                    wrap = true,
                )
            }
            card(height = 244.px, pad = 22.0, gap = 10.0) {
                text(
                    content = "i18n.label.language".translatable,
                    height = 18.px,
                    size = 12.0,
                    color = MUTED,
                    bold = true,
                )
                val languageCombo = draw(ComboBox(LANGUAGES) { it.label }) { combo ->
                    layout {
                        width = 100.percent
                        height = 44.px
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
                    combo.selectedIndex =
                        (LANGUAGES.indexOfFirst { it.code == I18N.currentLang }.coerceAtLeast(0))
                    combo.onSelectItem { language -> I18N.currentLang = language.code; applyLanguage() }
                }
                text(
                    content = "i18n.greeting".translatable,
                    height = 48.px,
                    size = 24.0,
                    color = BRAND_DEEP,
                    bold = true,
                )
                draw(FlexBox()) { row ->
                    layout {
                        width = 100.percent
                        height = 40.px
                    }
                    row.gap = 10.px
                    row.align = Align.CENTER
                    row.layout {
                        button(
                            label = "i18n.button.switch".translatable,
                            width = 220.px,
                            height = 38.px,
                            fill = BRAND,
                            foreground = Color.WHITE,
                        ) {
                            val current = LANGUAGES.indexOfFirst { it.code == I18N.currentLang }
                                .let { if (it < 0) 0 else it }
                            val nextIndex = (current + 1) % LANGUAGES.size
                            I18N.currentLang = LANGUAGES[nextIndex].code
                            applyLanguage()
                            languageCombo.selectedIndex = nextIndex
                        }
                    }
                }
            }
            card(height = 262.px, pad = 22.0, gap = 10.0) {
                text(
                    content = "i18n.compare.title".translatable,
                    height = 24.px,
                    size = 15.0,
                    color = INK,
                    bold = true,
                )
                text(
                    content = "i18n.compare.literal".translatable,
                    height = 18.px,
                    size = 12.0,
                    color = MUTED,
                )
                bareTextArea("i18n.greeting") { area ->
                    layout {
                        width = 100.percent
                        height = 26.px
                    }
                    style {
                        fontSize = 16.0.px
                        foreground = ROSE
                    }
                }
                text(
                    content = "i18n.compare.translatable".translatable,
                    height = 18.px,
                    size = 12.0,
                    color = MUTED,
                )
                bareTextArea("i18n.greeting".translatable) { area ->
                    layout {
                        width = 100.percent
                        height = 26.px
                    }
                    style {
                        fontSize = 16.0.px
                        foreground = MINT
                    }
                    area.bold = true
                }
                text(
                    content = "i18n.compare.note".translatable,
                    height = 40.px,
                    size = 12.0,
                    color = MUTED,
                    wrap = true,
                )
            }
        }
    }

    private fun bindVideoPane(pane: TabPane) {
        pane.addPage(
            tabLabel = "▶  VideoPane",
            title = "VideoPane",
            subtitle = "video.subtitle",
        ) {
            val video = draw(VideoPane("classpath:/media/sample.mp4")) { v ->
                layout {
                    width = 100.percent
                    height = 300.px
                }
                style {
                    corner = 12.px
                }
                v.loop = true
                v.volume = 0.8
            }
            card(height = 150.px, pad = 16.0, gap = 10.0) {
                text("video.text.1".translatable, height = 20.px, size = 13.0, color = INK, bold = true)
                draw(FlexBox()) { row ->
                    layout {
                        width = 100.percent
                        height = 40.px
                    }
                    row.gap = 8.px
                    row.align = Align.CENTER
                    row.layout {
                        button(
                            label = "video.label.1".translatable,
                            width = 108.px,
                            height = 38.px,
                            fill = BRAND,
                            foreground = Color.WHITE,
                        ) {
                            video.togglePlayPause()
                        }
                        button(label = "video.label.2".translatable, width = 80.px, height = 38.px) {
                            video.stop()
                        }
                        button(label = "video.label.3".translatable, width = 96.px, height = 38.px) {
                            video.muted = !video.muted
                        }
                        button(label = "video.label.4".translatable, width = 96.px, height = 38.px) {
                            video.loop = !video.loop
                        }
                        button(label = "video.label.5".translatable, width = 96.px, height = 38.px) {
                            video.seekTo(video.position + 5.seconds)
                        }
                        button(
                            label = "video.label.6".translatable,
                            width = 96.px,
                            height = 38.px,
                            fill = Color.WHITE,
                            foreground = BRAND_DEEP,
                            borderColor = Color.web("#c9d9f7"),
                        ) {
                            video.seekTo(Duration.ZERO)
                            video.play()
                        }
                    }
                }
                val statusText = text("video.text.2".translatable, height = 18.px, size = 12.0, color = MUTED)
                video.onReady { statusText.text("video.text.3".translatable) }
                video.onEnd { statusText.text("video.text.4".translatable) }
                video.onError { error -> statusText.text(I18N.content("video.prefix.error") + (error?.message ?: I18N.content("video.error.unknown"))) }
                video.onStatusChanged { status ->
                    val label = when (status.name) {
                        "READY" -> I18N.content("video.text.7")
                        "PLAYING" -> I18N.content("video.text.8")
                        "PAUSED" -> I18N.content("video.text.9")
                        "STOPPED" -> I18N.content("video.text.10")
                        "STALLED" -> I18N.content("video.text.11")
                        "HALTED" -> I18N.content("video.text.12")
                        "DISPOSED" -> I18N.content("video.text.13")
                        else -> status.name
                    }
                    statusText.text(I18N.content("video.prefix.status") + label)
                }
            }
        }
    }
}
