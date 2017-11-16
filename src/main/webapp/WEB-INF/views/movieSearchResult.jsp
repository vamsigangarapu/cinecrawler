<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Movie Results | Movie Crawler</title>
<meta name="description"
	content="A responsive, magazine-like website layout with a grid item animation effect when opening the content" />
<meta name="keywords"
	content="grid, layout, effect, animated, responsive, magazine, template, web design" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/normalize.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/fonts/font-awesome-4.3.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/displayMoviesStyle.css" />
<script
	src="${pageContext.request.contextPath}/static/js/modernizr.custom.js"></script>
</head>
<body>
	<div class="container">
		<button id="menu-toggle" class="menu-toggle">
			<span>Menu</span>
		</button>
		<div id="theSidebar" class="sidebar">
			<button class="close-button fa fa-fw fa-close"></button>
			<!-- <div class="codrops-links">
				<a class="codrops-icon codrops-icon--prev"
					href="http://tympanus.net/Tutorials/MotionBlurEffect/"
					title="Previous Demo"><span>Previous Demo</span></a> <a
					class="codrops-icon codrops-icon--drop"
					href="http://tympanus.net/codrops/?p=23872"
					title="Back to the article"><span>Back to the Codrops
						article</span></a>
			</div> -->
			<h1>
				<a href="/myapp/movie/?loginName=${sessionScope.username }" ><span>Movie Crawler</span></a>
			</h1>
			
			<form name="Movie Search" action="/myapp/movie/SubmitMovie/" method="get" accept-charset="utf-8">
				<center>
					<br> <input type="text" name="movieName" placeholder="Eg., Avengers" required  style="width: 250px; height: 30px; color: black">&nbsp;&nbsp; 
						<br> <br> <input type="submit" value="Search" style="font-size: 17px;  color: black">
				</center>
			</form>
			
			<!-- <nav class="codrops-demos">
					<a href="index.html">Demo 1</a>
					<a class="current-demo" href="index2.html">Demo 2</a>
				</nav>
				<div class="related">
					<h3>Related Demos</h3>
					<a href="http://tympanus.net/Development/BookPreview/">Book Preview</a>
					<a href="http://tympanus.net/Tutorials/ThumbnailGridExpandingPreview/">Thumbnail Grid</a>
					<a href="http://tympanus.net/Development/3DGridEffect/">3D Grid Effect</a>
				</div> -->
		</div>
		<div id="theGrid" class="main">
			<section class="grid"> <header class="top-bar">
			<h2 class="top-bar__headline">
				Movie Searched: <b>${movieName}</b>
			</h2>
			<div class="filter">
				<!-- <span>Filter by:</span>
							<span class="dropdown">Popular</span> -->

 						<c:choose>
	                    	<c:when test="${empty username}">
	                    		<span><a href="/myapp/movie/register/" method="get">Register</a> | <a href="/myapp/movie/login/" method="get">Login</a></span>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<span>Welcome ${sessionScope.username } | <a href = "/myapp/movie/logout/" method = "get" >Logout</a></span>
	                    		<input type="hidden" value="${sessionScope.username }" name="loginName">
	                    	</c:otherwise>
	                    </c:choose>

			</div>
			</header> <c:forEach items="${moviesearchresult.movieSearchResultObjects}"
				var="Result" varStatus="status">


				<a class="grid__item" href="#">

					<h2 class="title title--preview">${Result.movieName}</h2>
					<div class="loader"></div> <span class="category">${Result.id}</span>
					<div class="meta meta--preview">
						<img class="meta__avatar" src=${Result.imageUrl } alt="author04" />
						<span class="meta__date"><i class="fa fa-calendar-o"></i>
							${Result.year} </span> <span class="meta__reading-time"><i
							class="fa fa-clock-o"></i> ${Result.runtime}</span>
					</div>
				</a>

			</c:forEach> <!-- <footer class="page-meta">
						<span>Load more...</span>
					</footer> --> </section>
			<section class="content">
			<div class="scroll-wrap">
				<c:forEach items="${moviesearchresult.movieSearchResultObjects}"
					var="Result" varStatus="status">
					<article class="content__item"> <span
						class="category category--full">${Result.id}</span>
					<h2 class="title title--full">${Result.movieName}</h2>
					<div class="meta meta--full">
						<img class="meta__avatar" src=${Result.imageUrl } alt="author04" />
						<span class="meta__author">Sandra Paulson</span> <span
							class="meta__date"><i class="fa fa-calendar-o"></i>
							${Result.year}</span> <span class="meta__reading-time"><i
							class="fa fa-clock-o"></i> ${Result.runtime}</span> <span
							class="meta__misc meta__misc--seperator"><i
							class="fa fa-comments-o"></i> Critic Score: ${Result.criticScore }</span> <span class="meta__misc"><i
							class="fa fa-heart"></i> Audience Score: ${Result.audienceScore }</span>
						<nav class="article-nav">
						<button>
							<i class="fa fa-angle-left"></i> <span>Previous</span>
						</button>
						<button>
							<span>Next</span> <i class="fa fa-angle-right"></i>
						</button>
						</nav>
					</div>
					<p>${Result.synopsis}</p>
					<br>
					<br>
					<div class="castScroll" style="width: 40%">
						<table border="1">
						<th colspan="3">
						<div style="margin: auto; z-index: 300; background-color: #4CAF50; font-size: 20px; font-family: 'Arial Narrow', Arial, sans-serif; color: white;" align="center">
									<b><center>Cast</center></b>
								</div>
							</th>
						
							<tr style="border:thin inset #6E6E6E; text-align: justify;">
								<td width="300 px" style="color: white; border:thin inset #6E6E6E; text-align: justify;">
									

										<c:forEach var="listValue" items="${Result.cast}"
											varStatus="status">
											<div style="border:thin inset #6E6E6E; text-align: justify;">
												&nbsp;&nbsp;${listValue.key}</div>
												
										</c:forEach>
								
								</td>

								<td width="300 px" style="color: white; border:thin inset #6E6E6E; text-align: justify;">


										<c:forEach var="listValue" items="${Result.cast}"
											varStatus="status">
											<div style="border:thin inset #6E6E6E; text-align: justify;">
												&nbsp;&nbsp;${listValue.value}</div>
											
										</c:forEach>
								</td>
							</tr>
						</table>
					</div>

					<!-- <p>Taboo is itself an ambivalent word and by way of supplement we may add that the established meaning of this word might of itself have allowed us to guess what we have found as the result of extensive investigation, namely, that the taboo prohibition is to be explained as the result of an emotional ambivalence. A study of the oldest languages has taught us that at one time there were many such words which included their own contrasts so that they were in a certain sense ambivalent, though perhaps not exactly in the same sense as the word taboo[88]. Slight vocal modifications of this primitive word containing two opposite meanings later served to create a separate linguistic expression for the two opposites originally united in one word.</p>
							<p>The word taboo has had a different fate; with the diminished importance of the ambivalence which it connotes it has itself disappeared, or rather, the words analogous to it have vanished from the vocabulary. In a later connection I hope to be able to show that a tangible historic change is probably concealed behind the fate of this conception; that the word at first was associated with definite human relations which were characterized by great emotional ambivalence from which it expanded to other analogous relations.</p>
							<p>Unless we are mistaken, the understanding of taboo also throws light upon the nature and origin of _conscience_. Without stretching ideas we can speak of a taboo conscience and a taboo sense of guilt after the violation of a taboo. Taboo conscience is probably the oldest form in which we meet the phenomenon of conscience.</p>
							<p>If I judge my readers impressions correctly, I dare say that after hearing all that was said about taboo they are far from knowing what to understand by it and where to store it in their minds. This is surely due to the insufficient information I have given and to the omission of all discussions concerning the relation of taboo to superstition, to belief in the soul, and to religion. On the other hand I fear that a more detailed description of what is known about taboo would be still more confusing; I can therefore assure the reader that the state of affairs is really far from clear. We may say, however, that we deal with a series of restrictions which these primitive races impose upon themselves; this and that is forbidden without any apparent reason; nor does it occur to them to question this matter, for they subject themselves to these restrictions as a matter of course and are convinced that any transgression will be punished automatically in the most severe manner. There are reliable reports that innocent transgressions of such prohibitions have actually been punished automatically. For instance, the innocent offender who had eaten from a forbidden animal became deeply depressed, expected his death and then actually died. The prohibitions mostly concern matters which are capable of enjoyment such as freedom of movement and unrestrained intercourse; in some cases they appear very ingenious, evidently representing abstinences and renunciations; in other cases their content is quite incomprehensible, they seem to concern themselves with trifles and give the impression of ceremonials. Something like a theory seems to underlie all these prohibitions, it seems as if these prohibitions are necessary because some persons and objects possess a dangerous power which is transmitted by contact with the object so charged, almost like a contagion. The quantity of this dangerous property is also taken into consideration. Some persons or things have more of it than others and the danger is precisely in accordance with the charge. The most peculiar part of it is that any one who has violated such a prohibition assumes the nature of the forbidden object as if he had absorbed the whole dangerous charge. This power is inherent in all persons who are more or less prominent, such as kings, priests and the newly born, in all exceptional physical states such as menstruation, puberty and birth, in everything sinister like illness and death and in everything connected with these conditions by virtue of contagion or dissemination.</p>
							<p>First of all it must be said that it is useless to question savages as to the real motivation of their prohibitions or as to the genesis of taboo. According to our assumption they must be incapable of telling us anything about it since this motivation is unconscious to them. But following the model of the compulsive prohibition we shall construct the history of taboo as follows: Taboos are very ancient prohibitions which at one time were forced upon a generation of primitive people from without, that is, they probably were forcibly impressed upon them by an earlier generation. These prohibitions concerned actions for which there existed a strong desire. The prohibitions maintained themselves from generation to generation, perhaps only as the result of a tradition set up by paternal and social authority. But in later generations they have perhaps already become organized as a piece of inherited psychic property. Whether there are such innate ideas or whether these have brought about the fixation of the taboo by themselves or by co-operating with education no one could decide in the particular case in question. The persistence of taboo teaches, however, one thing, namely, that the original pleasure to do the forbidden still continues among taboo races. They therefore assume an _ambivalent attitude_ toward their taboo prohibitions; in their unconscious they would like nothing better than to transgress them but they are also afraid to do it; they are afraid just because they would like to transgress, and the fear is stronger than the pleasure. But in every individual of the race the desire for it is unconscious, just as in the neurotic.</p> <p>It seems like an obvious contradiction that persons of such perfection of power should themselves require the greatest care to guard them against threatening dangers, but this is not the only contradiction revealed in the treatment of royal persons on the part of savages. These races consider it necessary to watch over their kings to see that they use their powers in the right way; they are by no means sure of their good intentions or of their conscientiousness. A strain of mistrust is mingled with the motivation of the taboo rules for the king. The idea that early kingdoms are despotisms, says Frazer[59], in which the people exist only for the sovereign, is wholly inapplicable to the monarchies we are considering. On the contrary, the sovereign in them exists only for his subjects: his life is only valuable so long as he discharges the duties of his position by ordering the course of nature for his peoples benefit. So soon as he fails to do so, the care, the devotion, the religious homage which they had hitherto lavished on him cease and are changed into hatred and contempt; he is ignominiously dismissed and may be thankful if he escapes with his life. Worshipped as a god one day, he is killed as a criminal the next. But in this changed behaviour of the people there is nothing capricious or inconsistent. On the contrary, their conduct is quite consistent. If their king is their god he is or should be, also their preserver; and if he will not preserve them he must make room for another who will. So long, however, as he answers their expectations, there is no limit to the care which they take of him, and which they compel him to take of himself. A king of this sort lives hedged in by ceremonious etiquette, a network of prohibitions and observances, of which the intention is not to contribute to his dignity, much less to his comfort, but to restrain him from conduct which, by disturbing the harmony of nature, might involve himself, his people, and the universe in one common catastrophe. Far from adding to his comfort, these observances, by trammelling his every act, annihilate his freedom and often render the very life, which it is their object to preserve, a burden and sorrow to him.</p>
							<p>Excerpts from <a href="http://www.gutenberg.org/ebooks/41214">Totem and Taboo</a> by Sigmund Freud.</p> -->
					</article>
				</c:forEach>
			</div>
			<button class="close-button">
				<i class="fa fa-close"></i><span>Close</span>
			</button>
			</section>
		</div>
	</div>
	<!-- /container -->
	<script src="${pageContext.request.contextPath}/static/js/classie.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>
