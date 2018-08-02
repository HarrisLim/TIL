# jsp 파일 다중 업로드
  - 이 코드는 파일을 하나씩 올릴 수도 있고 여러개를 한 번에 잡아서 올릴 수도 있는 코드이다. 
  - 주의할 점
    - ajax를 이용하여 controller로 보낼 때는 formdata로 묶어서 보내야한다.
    - ajax를 이용하여 controller로 보낼 때는 processData : false와 contentType : false를 꼭 추가해야한다.
 
  - 간단 설명
    - view단에서 파일을 올리는 것을 배열에 하나 하나 저장한 후, 그 배열을 formData로 묶어서 back단으로 보낸다.
    - 미리보기 파일을 생성할 때마다 name을 1씩 증가시켜서 서로 다른 name을 주고, 삭제할 때 클릭된 현재 name을 받아서 배열에서 현재 사진을 찾아서 0으로 만들고, 실제 controller로 배열을 보낼 때는 0을 제거하고 보낸다. 
### jsp
```
	<form id="formId" name="fileForm" method="post" action="uploaddroomsubmit.do" enctype="multipart/form-data">
		<div class="custom-file" style="margin: 15px 0 20px 0;">
		<input type="file" class="custom-file-input" id="customFile" name="photo" id="photo" accept=".png, .jpg, .jpeg" onchange="readFile(this);" multiple>
		<label class="custom-file-label" for="customFile">사진을 올려주세요.</label>
		</div>
		<div id="status"></div>
		<div id="photos" class="row col-lg-10"></div>
	</form>
```
### js
```
	var uploadFiles = [];
	var z = 0;
    function readFile(input) {
        counter = input.files.length;
   		for(x = 0; x<counter; x++){
   			if (input.files && input.files[x]) {
	   			console.log("files["+x+"].name:  "+input.files[x].name);
   				var reader = new FileReader();
   				reader.onload = function (e) {
           			$("#photos").append('<div class="photo" style="width:200px; height:150px;margin:7px"><img src="'+e.target.result+'" class="img-thumbnail" style="width:100%;height:100%"><button type="button" class="btn" onclick="removePic(this)" name="'+(z++)+'"> X </button></div>');
   				};
   				reader.readAsDataURL(input.files[x]);
   				uploadFiles.push(input.files[x]);
   				console.log(uploadFiles);
   			}
        }
      }


  	var delCount = 0;
  	function removePic(x){
		if(confirm("선택한 사진을 지우시겠습니까?")){
			x.parentNode.parentNode.removeChild(x.parentNode);
			console.log("x.name: "+ x.name);
			uploadFiles[x.name] = 0;
			delCount++;
			for(var key in uploadFiles){
				console.log("key : "+ key+", obj: "+ uploadFiles[key].name);
			}
		}else
			return;
	}

  	function delIdel(){ // uploadFiles에 들어간 0을 삭제. (0은 삭제할 때 사용한 것) 
  		for(var i=0; i<uploadFiles.length; i++){
  			if(uploadFiles[i] === 0){
  				uploadFiles.splice(i, 1);
  				delIdel();
  			}
  		}
  		for(var j=0; j<uploadFiles.length;j++){
  			console.log("final: "+ uploadFiles[j]);
  		}
  	}

	function uploadImg(){
  	    var files = JSON.stringify(uploadFiles);
  	    
  	 	var form = $('#uploadForm')[0];
          var formData = new FormData(form);

          for (var index = 0; index < Object.keys(uploadFiles).length; index++) {
              formData.append('uploadFiles',uploadFiles[index]);
        }
  	  
  	    $.ajax({
  	        type: 'POST',
            enctype : 'multipart/form-data',
            processData : false, // 필수 추가
            contentType : false, // 필수 추가 
  	        dataType: 'JSON',
  	        data: formData, // 필수 ! formData 형식으로.
  	        url: 'imageupload.do',
  	        success: function(json) {
  	            alert("됐따 ~");
  	        },
  	        error: function(request,status,error){
  	            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
  	        }
  	    });
  	
  	}
```
### controller
```
	@ResponseBody
    @RequestMapping(value="house/imageupload.do", method=RequestMethod.POST)
    public int multiImageUpload(@RequestParam("uploadFiles")List<MultipartFile> images) {
        long sizeSum = 0;
        for(MultipartFile image : images) {
        	imgFiles.add(image);
            String originalName = image.getOriginalFilename();
            System.out.println("originalname: " + originalName); // 확인 가능
        }
        
        return 1;
    }
```