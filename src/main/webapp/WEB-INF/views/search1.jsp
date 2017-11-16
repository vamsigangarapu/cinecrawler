<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search for movie title here</title>
<style type="text/css">
.image-size {
	min-height: 1080px;
	
}

input {
	padding: 5px;
	font-size: 20px;
}
</style>

	<script type="text/javascript">
	
		//Array of images which you want to show: Use path you want.
		var images = new Array();
			
	    images[0] = 'http://s29.postimg.org/912bsm0mf/dataarancio.jpg';
		images[1] = 'http://s27.postimg.org/y6tl17eb7/velocita.jpg';
		var nextimage = 0;
		
		doSlideshow();
		
		function doSlideshow() {
			
			if (nextimage >= images.length) {
				nextimage = 0;
			}
			
			$('.bkg').css('background-image','url("'+images[nextimage++]+'")').fadeIn(500,function(){
				alert("heyyyyy world");
						setTimeout(doSlideshow, 4000);
					});
		}
	</script>
	
</head>
<body>
	<div class="bkg">
		<div class="image-size">
			<form action="/myapp/movie/login" method="post">
				<div align="right">
					<b>You want to login in order to see the near by theater list??</b><br>
					<input type="submit" value="login">
				</div>
			</form>
			<form name="Movie Search" action="/myapp/movie/SubmitMovie/" method="get" accept-charset="utf-8">
				<center>
					<label for="movieDB"><font size="33">Movie DB</font></label><br>
					<br> <input type="text" name="movieName" placeholder="Eg., Avengers" required> 
						<input type="submit" value="Search">
				</center>
			</form>
		</div>
	</div>


<form:form id="operatorForm" modelAttribute="MovieSearchResultObject">
    <h4>test</h4>

    <div id="operatorID"></div>

    <c:url var="findOperatingURL" value="/hello"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $.getJSON('${findOperatingURL}', {
                ajax: 'true'
            }, function (data) {
                alert("inside data!!!!");
                var html = '';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<div>' + data[i].id + '</div><div>' + data[i].movieName + '</div>';
                }
                $('#operatorID').html(html);
            });
        });
    </script>

    </div>
</form:form>

</body>
</html>