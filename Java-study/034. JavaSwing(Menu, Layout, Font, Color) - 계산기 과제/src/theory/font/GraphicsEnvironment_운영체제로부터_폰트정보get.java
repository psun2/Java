package theory.font;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

class GraphicsEnvironmentMain {

	String subject;

	public GraphicsEnvironmentMain(String subject) {
		this.subject = subject;
		init();
	}

	void init() {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		Font[] font = ge.getAllFonts();

		for (Font fo : font) {
			System.out.println(fo.getName());
		}
	}

}

public class GraphicsEnvironment_운영체제로부터_폰트정보get {

	public static void main(String[] args) {

		new GraphicsEnvironmentMain("운영체제로 부터 나의 폰트정보 가져오기");

	}

}

//Agency FB
//Agency FB Bold
//Algerian
//Arial
//Arial Black
//Arial Bold
//Arial Bold Italic
//Arial Italic
//Arial Narrow
//Arial Narrow Bold
//Arial Narrow Bold Italic
//Arial Narrow Italic
//Arial Rounded MT Bold
//Bahnschrift
//Bahnschrift Bold
//Bahnschrift Bold Condensed
//Bahnschrift Bold SemiCondensed
//Bahnschrift Condensed
//Bahnschrift Light
//Bahnschrift Light Condensed
//Bahnschrift Light SemiCondensed
//Bahnschrift Regular
//Bahnschrift SemiBold
//Bahnschrift SemiBold Condensed
//Bahnschrift SemiBold SemiCondensed
//Bahnschrift SemiCondensed
//Bahnschrift SemiLight
//Bahnschrift SemiLight Condensed
//Bahnschrift SemiLight SemiCondensed
//Baskerville Old Face
//Batang
//BatangChe
//Bauhaus 93
//Bell MT
//Bell MT Bold
//Bell MT Italic
//Berlin Sans FB
//Berlin Sans FB Bold
//Berlin Sans FB Demi Bold
//Bernard MT Condensed
//Blackadder ITC
//Bodoni MT
//Bodoni MT Black
//Bodoni MT Black Italic
//Bodoni MT Bold
//Bodoni MT Bold Italic
//Bodoni MT Condensed
//Bodoni MT Condensed Bold
//Bodoni MT Condensed Bold Italic
//Bodoni MT Condensed Italic
//Bodoni MT Italic
//Bodoni MT Poster Compressed
//Book Antiqua
//Book Antiqua Bold
//Book Antiqua Bold Italic
//Book Antiqua Italic
//Bookman Old Style
//Bookman Old Style Bold
//Bookman Old Style Bold Italic
//Bookman Old Style Italic
//Bookshelf Symbol 7
//Bradley Hand ITC
//Britannic Bold
//Broadway
//Brush Script MT Italic
//Calibri
//Calibri Bold
//Calibri Bold Italic
//Calibri Italic
//Calibri Light
//Calibri Light Italic
//Californian FB
//Californian FB Bold
//Californian FB Italic
//Calisto MT
//Calisto MT Bold
//Calisto MT Bold Italic
//Calisto MT Italic
//Cambria
//Cambria Bold
//Cambria Bold Italic
//Cambria Italic
//Cambria Math
//Candara
//Candara Bold
//Candara Bold Italic
//Candara Italic
//Candara Light
//Candara Light Italic
//Castellar
//Centaur
//Century
//Century Gothic
//Century Gothic Bold
//Century Gothic Bold Italic
//Century Gothic Italic
//Century Schoolbook
//Century Schoolbook Bold
//Century Schoolbook Bold Italic
//Century Schoolbook Italic
//Chiller
//Colonna MT
//Comic Sans MS
//Comic Sans MS Bold
//Comic Sans MS Bold Italic
//Comic Sans MS Italic
//Consolas
//Consolas Bold
//Consolas Bold Italic
//Consolas Italic
//Constantia
//Constantia Bold
//Constantia Bold Italic
//Constantia Italic
//Cooper Black
//Copperplate Gothic Bold
//Copperplate Gothic Light
//Corbel
//Corbel Bold
//Corbel Bold Italic
//Corbel Italic
//Corbel Light
//Corbel Light Italic
//Courier New
//Courier New Bold
//Courier New Bold Italic
//Courier New Italic
//Curlz MT
//Dialog.bold
//Dialog.bolditalic
//Dialog.italic
//Dialog.plain
//DialogInput.bold
//DialogInput.bolditalic
//DialogInput.italic
//DialogInput.plain
//Dotum
//DotumChe
//Dubai Bold
//Dubai Light
//Dubai Medium
//Dubai Regular
//Ebrima
//Ebrima Bold
//Edwardian Script ITC
//Elephant
//Elephant Italic
//Engravers MT
//Eras Bold ITC
//Eras Demi ITC
//Eras Light ITC
//Eras Medium ITC
//Felix Titling
//Footlight MT Light
//Forte
//Franklin Gothic Book
//Franklin Gothic Book Italic
//Franklin Gothic Demi
//Franklin Gothic Demi Cond
//Franklin Gothic Demi Italic
//Franklin Gothic Heavy
//Franklin Gothic Heavy Italic
//Franklin Gothic Medium
//Franklin Gothic Medium Cond
//Franklin Gothic Medium Italic
//Freestyle Script
//French Script MT
//Gabriola
//Gadugi
//Gadugi Bold
//Garamond
//Garamond Bold
//Garamond Italic
//Georgia
//Georgia Bold
//Georgia Bold Italic
//Georgia Italic
//Gigi
//Gill Sans MT
//Gill Sans MT Bold
//Gill Sans MT Bold Italic
//Gill Sans MT Condensed
//Gill Sans MT Ext Condensed Bold
//Gill Sans MT Italic
//Gill Sans Ultra Bold
//Gill Sans Ultra Bold Condensed
//Gloucester MT Extra Condensed
//Goudy Old Style
//Goudy Old Style Bold
//Goudy Old Style Italic
//Goudy Stout
//Gulim
//GulimChe
//Gungsuh
//GungsuhChe
//HY견고딕
//HY견명조
//HY궁서B
//HY그래픽M
//HY목각파임B
//HY신명조
//HY얕은샘물M
//HY엽서L
//HY엽서M
//HY중고딕
//HY헤드라인M
//Haettenschweiler
//Harlow Solid Italic
//Harrington
//High Tower Text
//High Tower Text Italic
//HoloLens MDL2 Assets
//HyhwpEQ
//Impact
//Imprint MT Shadow
//Informal Roman
//Ink Free
//Javanese Text
//Jokerman
//Juice ITC
//Kristen ITC
//Kunstler Script
//Leelawadee
//Leelawadee Bold
//Leelawadee UI
//Leelawadee UI Bold
//Leelawadee UI Semilight
//Lucida Bright
//Lucida Bright Demibold
//Lucida Bright Demibold Italic
//Lucida Bright Italic
//Lucida Bright Regular
//Lucida Calligraphy Italic
//Lucida Console
//Lucida Fax Demibold
//Lucida Fax Demibold Italic
//Lucida Fax Italic
//Lucida Fax Regular
//Lucida Handwriting Italic
//Lucida Sans Demibold
//Lucida Sans Demibold Italic
//Lucida Sans Demibold Roman
//Lucida Sans Italic
//Lucida Sans Regular
//Lucida Sans Typewriter Bold
//Lucida Sans Typewriter Bold Oblique
//Lucida Sans Typewriter Oblique
//Lucida Sans Typewriter Regular
//Lucida Sans Unicode
//MS Gothic
//MS Outlook
//MS PGothic
//MS Reference Sans Serif
//MS Reference Specialty
//MS UI Gothic
//MT Extra
//MV Boli
//Magneto Bold
//Maiandra GD
//Marlett
//Matura MT Script Capitals
//Microsoft Himalaya
//Microsoft JhengHei
//Microsoft JhengHei Bold
//Microsoft JhengHei Light
//Microsoft JhengHei UI
//Microsoft JhengHei UI Bold
//Microsoft JhengHei UI Light
//Microsoft New Tai Lue
//Microsoft New Tai Lue Bold
//Microsoft PhagsPa
//Microsoft PhagsPa Bold
//Microsoft Sans Serif
//Microsoft Tai Le
//Microsoft Tai Le Bold
//Microsoft Uighur
//Microsoft Uighur Bold
//Microsoft YaHei
//Microsoft YaHei Bold
//Microsoft YaHei Light
//Microsoft YaHei UI
//Microsoft YaHei UI Bold
//Microsoft YaHei UI Light
//Microsoft Yi Baiti
//MingLiU-ExtB
//MingLiU_HKSCS-ExtB
//Mistral
//Modern No. 20
//Mongolian Baiti
//Monospaced.bold
//Monospaced.bolditalic
//Monospaced.italic
//Monospaced.plain
//Monotype Corsiva
//Myanmar Text
//Myanmar Text Bold
//NSimSun
//NewJumja
//Niagara Engraved
//Niagara Solid
//Nirmala UI
//Nirmala UI Bold
//Nirmala UI Semilight
//OCR A Extended
//Old English Text MT
//Onyx
//PMingLiU-ExtB
//Palace Script MT
//Palatino Linotype
//Palatino Linotype Bold
//Palatino Linotype Bold Italic
//Palatino Linotype Italic
//Papyrus
//Parchment
//Perpetua
//Perpetua Bold
//Perpetua Bold Italic
//Perpetua Italic
//Perpetua Titling MT Bold
//Perpetua Titling MT Light
//Playbill
//Poor Richard
//Pristina
//Rage Italic
//Ravie
//Rockwell
//Rockwell Bold
//Rockwell Bold Italic
//Rockwell Condensed
//Rockwell Condensed Bold
//Rockwell Extra Bold
//Rockwell Italic
//SansSerif.bold
//SansSerif.bolditalic
//SansSerif.italic
//SansSerif.plain
//Script MT Bold
//Segoe MDL2 Assets
//Segoe Print
//Segoe Print Bold
//Segoe Script
//Segoe Script Bold
//Segoe UI
//Segoe UI Black
//Segoe UI Black Italic
//Segoe UI Bold
//Segoe UI Bold Italic
//Segoe UI Emoji
//Segoe UI Historic
//Segoe UI Italic
//Segoe UI Light
//Segoe UI Light Italic
//Segoe UI Semibold
//Segoe UI Semibold Italic
//Segoe UI Semilight
//Segoe UI Semilight Italic
//Segoe UI Symbol
//Serif.bold
//Serif.bolditalic
//Serif.italic
//Serif.plain
//Showcard Gothic
//SimSun
//SimSun-ExtB
//Sitka Banner
//Sitka Banner Bold
//Sitka Banner Bold Italic
//Sitka Banner Italic
//Sitka Display
//Sitka Display Bold
//Sitka Display Bold Italic
//Sitka Display Italic
//Sitka Heading
//Sitka Heading Bold
//Sitka Heading Bold Italic
//Sitka Heading Italic
//Sitka Small
//Sitka Small Bold
//Sitka Small Bold Italic
//Sitka Small Italic
//Sitka Subheading
//Sitka Subheading Bold
//Sitka Subheading Bold Italic
//Sitka Subheading Italic
//Sitka Text
//Sitka Text Bold
//Sitka Text Bold Italic
//Sitka Text Italic
//Snap ITC
//Stencil
//Sylfaen
//Symbol
//Tahoma
//Tahoma Bold
//Tempus Sans ITC
//Times New Roman
//Times New Roman Bold
//Times New Roman Bold Italic
//Times New Roman Italic
//Trebuchet MS
//Trebuchet MS Bold
//Trebuchet MS Bold Italic
//Trebuchet MS Italic
//Tw Cen MT
//Tw Cen MT Bold
//Tw Cen MT Bold Italic
//Tw Cen MT Condensed
//Tw Cen MT Condensed Bold
//Tw Cen MT Condensed Extra Bold
//Tw Cen MT Italic
//Verdana
//Verdana Bold
//Verdana Bold Italic
//Verdana Italic
//Viner Hand ITC
//Vivaldi Italic
//Vladimir Script
//Webdings
//Wide Latin
//Wingdings
//Wingdings 2
//Wingdings 3
//Yu Gothic Bold
//Yu Gothic Light
//Yu Gothic Medium
//Yu Gothic Regular
//Yu Gothic UI Bold
//Yu Gothic UI Light
//Yu Gothic UI Regular
//Yu Gothic UI Semibold
//Yu Gothic UI Semilight
//굴림
//굴림체
//궁서
//궁서체
//돋움
//돋움체
//맑은 고딕
//맑은 고딕 Bold
//맑은 고딕 Semilight
//문체부 궁체 정자체
//문체부 궁체 흘림체
//문체부 돋음체
//문체부 바탕체
//문체부 쓰기 정체
//문체부 쓰기 흘림체
//문체부 제목 돋음체
//문체부 제목 바탕체
//문체부 훈민정음체
//바탕
//바탕체
//새굴림
//함초롬돋움
//함초롬돋움 Bold
//함초롬돋움 확장
//함초롬바탕
//함초롬바탕 Bold
//함초롬바탕 확장
//함초롬바탕 확장B
//함초롬바탕 확장B Bold
//휴먼둥근헤드라인
//휴먼매직체
//휴먼모음T
//휴먼아미체
//휴먼엑스포
//휴먼옛체
//휴먼편지체
