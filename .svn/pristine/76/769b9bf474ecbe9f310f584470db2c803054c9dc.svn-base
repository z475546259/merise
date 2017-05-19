(function($) {
	/**
	 * contentId:点击放大显示内容div元素Id.
	 */
	$.fn.zoom = function(options) {
		var settings={
				zoomDom:$('#' + options.contentId).hide(),
				zoomContent:$('#'+ options.contentId + ' .content'),
				zoomedIn:false,
				openedImage:null,
				windowWidth:$(window).width(), 
				windowHeight:$(window).height(), 
				rootPath:options.rootPath,
				ulDom:this
		}
		function open(event) {
			if (event)
				event.preventDefault();
			var link = $(this), src = link.attr('href');
			if (!src)
				return;
			var image = $(new Image()).hide();
			$('#' + options.contentId + ' .previous, #'+ options.contentId + ' .next').show();
			if (link.hasClass('zoom'))
				$('#' + options.contentId + ' .previous, #'+ options.contentId + ' .next').hide();
			if (!settings.zoomedIn) {
				settings.zoomedIn = true;
				settings.zoomDom.show();
				$('body').addClass('zoomed');
			}
			var tempHtml="<div class='large'></div>";
			settings.zoomContent.html(image).delay(50).addClass('loading');
			settings.zoomContent.append(tempHtml);
			image.load(render).attr('src', src);
			settings.openedImage = link;

			function render() {
				var image = $(this), borderWidth = parseInt(settings.zoomContent.css('border-left-width'));
				if (isNaN(borderWidth)) {
					borderWidth = 0;
				}
				maxImageWidth = settings.windowWidth - (borderWidth * 2),
						maxImageHeight = settings.windowHeight - (borderWidth * 2),
						imageWidth = image.width(), imageHeight = image
								.height();
				if (imageWidth == settings.zoomContent.width()
						&& imageWidth <= maxImageWidth
						&& imageHeight == settings.zoomContent.height()
						&& imageHeight <= maxImageHeight) {
					show(image);
					return;
				}
				if (imageWidth > maxImageWidth || imageHeight > maxImageHeight) {
					var desiredHeight = maxImageHeight < imageHeight ? maxImageHeight
							: imageHeight, desiredWidth = maxImageWidth < imageWidth ? maxImageWidth
							: imageWidth;
					if (desiredHeight / imageHeight <= desiredWidth
							/ imageWidth) {
						image.width(imageWidth * desiredHeight / imageHeight);
						image.height(desiredHeight);
					} else {
						image.width(desiredWidth);
						image.height(imageHeight * desiredWidth / imageWidth);
					}
				}
				settings.zoomContent.animate({
					width : (image.width()*0.7),
					height : (image.height()*0.7),
					marginTop : -(image.height()*0.7 / 2) - borderWidth,
					marginLeft : -(image.width()*0.7 / 2) - borderWidth
				}, 200, function() {
					show(image);
				});

				function show(image) {
					image.css({"width":image.width()*0.7,"height":image.height()*0.7})
					image.show();
					settings.zoomContent.removeClass('loading');
					magnifyingGlass();
				}
				var native_width = 0;
				var native_height = 0;
				
				function magnifyingGlass() {
					$('.large').css("background","url("+image.attr('src')+") no-repeat");
					var img_obj = new Image();
					img_obj.src = image.attr('src');
					native_width = img_obj.width;
					native_height = img_obj.height;
					settings.zoomContent.mousemove(function(e) {
						var magnify_offset = $(this).offset();
						var mouse_x = e.pageX - magnify_offset.left;
						var mouse_y = e.pageY - magnify_offset.top;
						if (mouse_x > 0 && mouse_y > 0
								&& mouse_x < $(this).width()
								&& mouse_y < $(this).height()) {
							$('.large').fadeIn(100);
						} else {
							$('.large').fadeOut(100);
						}
						if ($('.large').is(':visible')) {
							var rx = Math.round(mouse_x / (image.width())
									* (native_width) - $('.large').width() / 2)
									* -1;
							var ry = Math.round(mouse_y / (image.height())
									* native_height - $('.large').height() / 2)
									* -1;
							var bgp = rx + 'px ' + ry+ 'px';
							var gx = mouse_x - $('.large').width() / 2;
							var gy = mouse_y - $('.large').height() / 2;
							$('.large').css({
								'left' : gx,
								'top' : gy,
								'backgroundPosition' : bgp
							})

						}
					})
				}
			}
		}

		function openPrevious() {
			var prev = settings.openedImage.parent('li').prev();
			if (prev.length == 0)
				prev = $(settings.ulDom).find('li:last-child');
			prev.find('a').trigger('click');
		}

		function openNext() {
			var next = settings.openedImage.parent('li').next();
			if (next.length == 0)
				next = $(settings.ulDom).find('li:first-child');
			next.children('a').trigger('click');
		}

		function close(event) {
			if (event)
				event.preventDefault();
			settings.zoomedIn = false;
			settings.openedImage = null;
			settings.zoomDom.hide();
			$('body').removeClass('zoomed');
			settings.zoomContent.empty();
		}

		function changeImageDimensions() {
			settings.windowWidth = $(window).width();
			settings.windowHeight = $(window).height();
		}
		
		/**
		 * 填充图片元素
		 */
		function appendImageElement(iamgeUrlsObj,separate){
			var imageUrlArr;
			if(typeof(iamgeUrlsObj)=='object'&&iamgeUrlsObj.constructor==Array){
				imageUrlArr=iamgeUrlsObj;
			}else if(typeof(iamgeUrlsObj)=='string'&&iamgeUrlsObj.constructor==String){
				if(separate==undefined){
					separate=',';
				}
				imageUrlArr=iamgeUrlsObj.split(separate);
			}
			$.each(imageUrlArr,function(){
				$(settings.ulDom).append('<li><a href="'+settings.rootPath+this+'"><img src="'+settings.rootPath+this+'" /></a></li>');
			})
			bindNavigation();
		}
		function bindModelClick(){
			$('#' + options.contentId + ' .close').on('click', close);
			$('#' + options.contentId + ' .previous').on('click', openPrevious);
			$('#' + options.contentId + ' .next').on('click', openNext);
			
		}
		function bindNavigation(){
			settings.zoomDom.on('click', function(event) {
				event.preventDefault();
				if ($(event.target).attr('id') == options.contentId)
					close();
			});
			$(document).keydown(function(event) {
				if (!settings.openedImage)
					return;
				if (event.which == 38 || event.which == 40)
					event.preventDefault();
				if (event.which == 27)
					close();
				if (event.which == 37 && !settings.openedImage.hasClass('zoom'))
					openPrevious();
				if (event.which == 39 && !settings.openedImage.hasClass('zoom'))
					openNext();
			});

			if ($(settings.ulDom).find('li a').length == 1)
				$(settings.ulDom).find('li a').addClass('zoom');
			$(settings.ulDom).find('.zoom').on('click', open);
			$(settings.ulDom).find('li a').on('click', open);
		}
		
		bindNavigation();
		bindModelClick();
		(function bindChangeImageDimensions() {
			$(window).on('resize', changeImageDimensions);
		})();

		(function bindScrollControl() {
			$(window).on('mousewheel DOMMouseScroll', function(event) {
				if (!settings.openedImage)
					return;
				event.stopPropagation();
				event.preventDefault();
				event.cancelBubble = false;
			});
		})();
		return {
			//绑定点击动画
			bindNavigation:bindNavigation,
			//填充图片元素
			appendImageElement:appendImageElement
		}
	}
	
})(jQuery);