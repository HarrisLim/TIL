# Imageslider 사용법
  Fullhouse프로젝트를 하던 중, 사진을 여러장 올려야 할 필요가 있어서 검색해보던 중 Imagesilder를 찾게 되었다.<br>
  - 경로
    [just javascript](http://www.menucool.com/javascript-image-slider)<br>
    [responsive](http://www.menucool.com/responsive-slider)<br>
    [using jquery](http://www.menucool.com/jquery-slider)

  - 사용법
    1. 위의 경로에서 소스코드를 다운로드 받으면 여러개의 Demo버전을 사용할 수 있다.<br>
    2. 코드 추가.(5번 Demo 기준) (Demo마다 사용법이 다르다.)
```
<!DOCTYPE html>
<html> 
	<head>
		<title>Demo 사용법</title>
		<link href="themes/1/js-image-slider.css" rel="stylesheet" type="text/css"/>
		<script src="themes/1/js-image-slider.js" type="text/javascript"></script>
		<link href="generic.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<div id="sliderFrame">
			<div id="slider"> <!-- 여기에 사진을 여러장 추가 가능. -->
				<img src="images/image-slider-1.jpg"/>
				<img src="images/image-slider-2.jpg"/>
				<img src="images/image-slider-3.jpg" alt="여기에 사진 설명을 넣을 수 있다." />
				<img src="images/image-slider-4.jpg"/>
				<img src="images/image-slider-5.jpg"/>
			</div>
			<div id="thumbs"> <!-- 밑에 작게 나오는 사진. 위의 것과 맞춰야 클릭했을 때 제대로 선택된다. -->
	            <div class="thumb"><img src="images/thumb1.jpg"></div>
	            <div class="thumb"><img src="images/thumb2.jpg"></div>
	            <div class="thumb"><img src="images/thumb3.jpg"></div>
	            <div class="thumb"><img src="images/thumb4.jpg"></div>
	            <div class="thumb"><img src="images/thumb5.jpg"></div>
	        <div>
		</div>
	</body>
</html>
```

    3. 코드 Comtomizing
      - generic.css는 Imageslider의 style은 아니라서 필요없다면 지워도 된다. 
      - [silder effect](http://www.menucool.com/slider/javascript-image-slider-demo1)에서 effect확인
      - js-image-slider.js에서 코드를 나의 입맛에 맞게 수정하자. 
```
var sliderOptions=
{
	sliderId: "slider",
	startSlide: 0,  // 어떤 사진부터 시작하는지.
	effect: "13",  // 이동할 때 어떤 effect를 사용할 것인지, 위의 링크 참고
	effectRandom: false,  // 말 그래도 effect를 random하게.
	pauseTime: 2600,  // 사진에 멈췄을 때 멈춰있는 시간
	transitionTime: 500,  // 사진이 넘어가는 동안의 시간
	slices: 12,
	boxes: 9,  // effect중에 박스로 넘어갈 때의 박스 개수
	hoverPause: 1,
	autoAdvance: false,  // 자동 화면 변환 
	captionOpacity: 1,
	captionEffect: "fade",
	thumbnailsWrapperId: "thumbs",
	m: false,
	license: "mylicense"  // 유료면 라이센스가 있다
};
```