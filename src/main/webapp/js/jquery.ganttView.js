/*
jQuery.ganttView v.0.8.8
Copyright (c) 2010 JC Grubbs - jc.grubbs@devmynd.com
MIT License Applies
*/

/*
Options
-----------------
showWeekends: boolean
data: object
cellWidth: number
cellHeight: number
slideWidth: number
dataUrl: string
behavior: {
	clickable: boolean,
	draggable: boolean,
	resizable: boolean,
	onClick: function,
	onDrag: function,
	onResize: function
}
*/

(function (jQuery) {
	
    jQuery.fn.ganttView = function () {
    	
    	var args = Array.prototype.slice.call(arguments);
    	
    	if (args.length == 1 && typeof(args[0]) == "object") {
        	build.call(this, args[0]);
    	}
    	
    	if (args.length == 2 && typeof(args[0]) == "string") {
    		handleMethod.call(this, args[0], args[1]);
    	}
    };
    
    function build(options) { // 간트차트 default값 설정 
    	
    	var els = this;
        var defaults = {
            showWeekends: true,
            cellWidth: 21,
            cellHeight: 31,
            slideWidth: 400,
            vHeaderWidth: 100,
            behavior: {
            	clickable: true,
            	draggable: true,
            	resizable: true
            }
        };
        
        var opts = jQuery.extend(true, defaults, options);

		if (opts.data) { // ganttview함수에서 data가 넘어온 경우
		
			build();
		} else if (opts.dataUrl) { //외부 URL에서 Json데이터를 가져올 경우 
			console.log(opts.dataUrl);
			jQuery.getJSON(opts.dataUrl, function (data) { 
				opts.data = data; 
				build(); 
				});
		}

		//  data를 받아 간트차트를 만들어주는 함수 
		function build() {
			
			//var minDays = Math.floor((opts.slideWidth / opts.cellWidth)  + 5);
		/*	var startEnd = DateUtils.getBoundaryDatesFromData(opts.data, minDays);*/ //데이터의 정보를 받아, 간트차트의 시작날짜와 마지막 날짜를 배열로 저장
		
			
			/////////////////////간트차트의 시작과 끝 날짜를 정해주는 부분 >> 프로젝트의 시작날짜~끝나는 날짜 + alpha day
			opts.start = new Date(2018,00,01);
			opts.end = new Date(2020,04,05);
		
	        els.each(function () {

	            var container = jQuery(this);
	        
	            var div = jQuery("<div>", { "class": "ganttview" }); // 간트차트 Div 생성
	            new Chart(div, opts).render(); //  Chart를 생성하고, 
				container.append(div); // container에 append 컨테이너는 간트차트 전체
				
				var w = jQuery("div.ganttview-vtheader", container).outerWidth() + // vheader는 리스트나오는 부분
					jQuery("div.ganttview-slide-container", container).outerWidth(); // zheader는 차트 부분
	            container.css("width", (w + 2) + "px");
	            
	            new Behavior(container, opts).apply(); 
	            
	        });
		}
    }

	function handleMethod(method, value) {
		
		if (method == "setSlideWidth") {
			var div = $("div.ganttview", this);
			div.each(function () {
				var vtWidth = $("div.ganttview-vtheader", div).outerWidth();
				$(div).width(vtWidth + value + 1);
				$("div.ganttview-slide-container", this).width(value);
			});
		}
	}

	var Chart = function(div, opts) {
		// div는 우리가 만들 간트차트
		// Opts은 57줄에 정의되어 있는 것을 참고 
		
		function render() {
			addVtHeader(div, opts.data, opts.cellHeight); //무슨 함수인지 찾아볼것 

            var slideDiv = jQuery("<div>", { // 차트부분 생성
                "class": "ganttview-slide-container",
                "css": { "width": opts.slideWidth + "px" }
            });
			
            dates = getDates(opts.start, opts.end);
            addHzHeader(slideDiv, dates, opts.cellWidth);
            addGrid(slideDiv, opts.data, dates, opts.cellWidth, opts.showWeekends);
            addBlockContainers(slideDiv, opts.data);
            addBlocks(slideDiv, opts.data, opts.cellWidth, opts.start);
            div.append(slideDiv);
            applyLastClass(div.parent());
		}
		
		var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

		// Creates a 3 dimensional array [year][month][day] of every day 
		// between the given start and end dates
        function getDates(start, end) { // 간트차트의 시작날짜와 끝 날짜를 설정해주는 부분이라고 생각하면 될듯 

            var dates = [];
			dates[start.getFullYear()] = [];
			dates[start.getFullYear()][start.getMonth()] = [start]
			var last = start;
			while (last.compareTo(end) == -1) {
				var next = last.clone().addDays(1);
				if (!dates[next.getFullYear()]) { dates[next.getFullYear()] = []; }
				if (!dates[next.getFullYear()][next.getMonth()]) { 
					dates[next.getFullYear()][next.getMonth()] = []; 
				}
				dates[next.getFullYear()][next.getMonth()].push(next);
				last = next;
			}
			return dates;
        }
        
        // task리스트 부분을 생성하는 함수 >> 디자인적으로 아마 수정이 필요할 듯 
        function addVtHeader(div, data, cellHeight) {
            var headerDiv = jQuery("<div>", { "class": "ganttview-vtheader" });
            for (var i = 0; i < data.length; i++) { //데이터의 갯수만큼 다음과 같은 작업을 반복적으로 진행
                var itemDiv = jQuery("<div>", { "class": "ganttview-vtheader-item" }); // Main Task의 Div생성
                itemDiv.append(jQuery("<div>", {
                    "class": "ganttview-vtheader-item-name",
                    "css": { "height": (data[i].series.length * cellHeight) + "px" } // series(하부 task)의 갯수를 세어 Div의 높이 조절
                })); // 생성한 div에 Main task입력 
                
                var seriesDiv = jQuery("<div>", { "class": "ganttview-vtheader-series" });
                for (var j = 0; j < data[i].series.length; j++) { // Series(하부태스크)를 위한 div를 생성해 이름 입력
                	var taskname = jQuery("<p>",{"class":"list-task-name"}).append(data[i].series[j].name);
                    seriesDiv.append(jQuery("<div>", { "class": "ganttview-vtheader-series-name" })
						.append(taskname));
                }
                itemDiv.append(seriesDiv);
                headerDiv.append(itemDiv);
            }
            div.append(headerDiv);
        }
        
        // 간트차트 부분을 생성하는 함수 >> 이 역시 활용 방법에 따라 수정 필요
        function addHzHeader(div, dates, cellWidth) { 
            var headerDiv = jQuery("<div>", { "class": "ganttview-hzheader" }); //간트차트 전체 div
            var monthsDiv = jQuery("<div>", { "class": "ganttview-hzheader-months" }); // 월에 대한 정보가 들어가는 div
            var daysDiv = jQuery("<div>", { "class": "ganttview-hzheader-days" }); // 일에 대한 정보가 들어가는 div
            var totalW = 0;
            console.log(dates)
			for (var y in dates) {
				for (var m in dates[y]) {
					console.log(m);
					console.log(y);
					//console.log(dates[y][m]); 해당 년월 계산하여 그 달의 일수 계산 
					var w = dates[y][m].length * cellWidth; //해당 연과 달의 일수를 세어 간트차트의 width를 결정
					totalW = totalW + w;
					monthsDiv.append(jQuery("<div>", {
						"class": "ganttview-hzheader-month",
						"css": { "width": (w - 1) + "px" }
					}).append(monthNames[m] + "/" + y));
					for (var d in dates[y][m]) {
						daysDiv.append(jQuery("<div>", { "class": "ganttview-hzheader-day" })
							.append(dates[y][m][d].getDate()));
					}
				}
			}
            monthsDiv.css("width", totalW + "px");
            daysDiv.css("width", totalW + "px");
            headerDiv.append(monthsDiv).append(daysDiv);
            div.append(headerDiv);
        }
        
        //cell 칸 만들어 주기 
        
        function addGrid(div, data, dates, cellWidth, showWeekends) {
            var gridDiv = jQuery("<div>", { "class": "ganttview-grid" });
            var rowDiv = jQuery("<div>", { "class": "ganttview-grid-row" });
			for (var y in dates) {
				for (var m in dates[y]) {
					for (var d in dates[y][m]) {
						var cellDiv = jQuery("<div>", { "class": "ganttview-grid-row-cell" });
						if (DateUtils.isWeekend(dates[y][m][d]) && showWeekends) { 
							cellDiv.addClass("ganttview-weekend"); 
						}
						rowDiv.append(cellDiv);
					}
				}
			}
            var w = jQuery("div.ganttview-grid-row-cell", rowDiv).length * cellWidth;
            rowDiv.css("width", w + "px");
            gridDiv.css("width", w + "px");
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < data[i].series.length; j++) {
                    gridDiv.append(rowDiv.clone());
                }
            }
            div.append(gridDiv);
        }

        //Task 블록 row 생성 >> Work load를 위해서 수정되어야 할 듯??
        function addBlockContainers(div, data) {
            var blocksDiv = jQuery("<div>", { "class": "ganttview-blocks" });
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < data[i].series.length; j++) { //task의 갯수만큼 만들어 간트차트에 붙힘
                    blocksDiv.append(jQuery("<div>", { "class": "ganttview-block-container" }));
                }
            }
            div.append(blocksDiv);
        }
        
        //Task 블록 추가해주기
        function addBlocks(div, data, cellWidth, start) {
            var rows = jQuery("div.ganttview-blocks div.ganttview-block-container", div);
            var rowIdx = 0; 
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < data[i].series.length; j++) {
                    var series = data[i].series[j];
                    
                    if(!(series.start == null) || !(series.end == null)){ // Task의 시작날짜 혹은 끝 날짜가 지정되어 있을 경우 블록 추가
                    	
                    	var size = DateUtils.daysBetween(series.start, series.end) + 1;
                    	var offset = DateUtils.daysBetween(start, series.start);
                    	var block = jQuery("<div>", {
                    		"class": "ganttview-block",
                    		"title": series.name + ", " + size + " days",
                    		"css": {
                    			"width": ((size * cellWidth) - 9) + "px",
                    			"margin-left": ((offset * cellWidth) + 3) + "px"
                    			////////////추후 이 자리에 Task의 ID를 넣어주어야 합니다
                    		}
                    	});
                    	addBlockData(block, data[i], series);
                    	if (data[i].series[j].color) { // 블록색 지정
                    		block.css("background-color", data[i].series[j].color);
                    	}
                    	block.append(jQuery("<div>", { "class": "ganttview-block-text" }).text(size)); //블록의 남은기간 표시
                    	jQuery(rows[rowIdx]).append(block);
                    }
                    rowIdx = rowIdx + 1;
                }
            }
        }
        
        function addBlockData(block, data, series) {
        	// This allows custom attributes to be added to the series data objects
        	// and makes them available to the 'data' argument of click, resize, and drag handlers
        	var blockData = { id: data.id, name: data.name };
        	jQuery.extend(blockData, series);
        	block.data("block-data", blockData);
        }

        function applyLastClass(div) {
            jQuery("div.ganttview-grid-row div.ganttview-grid-row-cell:last-child", div).addClass("last");
            jQuery("div.ganttview-hzheader-days div.ganttview-hzheader-day:last-child", div).addClass("last");
            jQuery("div.ganttview-hzheader-months div.ganttview-hzheader-month:last-child", div).addClass("last");
        }
		
		return {
			render: render
		};
	} //end chart

	var Behavior = function (div, opts) {
		
		function apply() {
			
			if (opts.behavior.clickable) { 
            	bindBlockClick(div, opts.behavior.onClick); 
        	}
        	
            if (opts.behavior.resizable) { 
            	bindBlockResize(div, opts.cellWidth, opts.start, opts.behavior.onResize); 
        	}
            
            if (opts.behavior.draggable) { 
            	bindBlockDrag(div, opts.cellWidth, opts.start, opts.behavior.onDrag); 
        	}
		}

		//클릭 이벤트 함수
        function bindBlockClick(div, callback) {
            jQuery("div.ganttview-block", div).live("click", function () {
                if (callback) { callback(jQuery(this).data("block-data")); }
            });
        }
        
        
        //리사이즈 이벤트 함수
        function bindBlockResize(div, cellWidth, startDate, callback) {
        	jQuery("div.ganttview-block", div).resizable({ //리사이즈 이벤트가 발생하게 되면
        		grid: cellWidth, 
        		handles: "e,w", // 동쪽, 서쪽으로만 드래그 가능하도록 >> s 와 n을 추가하면 남쪽 북쪽도 리사이즈 가능 
        		stop: function () { //드래그가 멈추면!
        	
        			var block = jQuery(this);
        			updateDataAndPosition(div, block, cellWidth, startDate);
        			if (callback) { callback(block.data("block-data")); }
        		}
        	});
        }
        
        // 드래그 이벤트 함수
        function bindBlockDrag(div, cellWidth, startDate, callback) {
        	jQuery("div.ganttview-block", div).draggable({
        		axis: "x", // 드래그 가능 방향 >> y 추가시 위아래로 가능   
        		grid: [cellWidth, cellWidth],
        		stop: function () {  //드래그가 멈추면 발생
        			var block = jQuery(this);
        			updateDataAndPosition(div, block, cellWidth, startDate);
        			if (callback) { callback(block.data("block-data")); }
        		}
        	});
        }
        
        function updateDataAndPosition(div, block, cellWidth, startDate) {
        	var container = jQuery("div.ganttview-slide-container", div);
        	var scroll = container.scrollLeft();
			var offset = block.offset().left - container.offset().left - 1 + scroll;
			
			// Set new start date
			var daysFromStart = Math.round(offset / cellWidth);
			var newStart = startDate.clone().addDays(daysFromStart);
			block.data("block-data").start = newStart;

			// Set new end date
        	var width = block.outerWidth();
			var numberOfDays = Math.round(width / cellWidth) - 1;
			block.data("block-data").end = newStart.clone().addDays(numberOfDays);
			jQuery("div.ganttview-block-text", block).text(numberOfDays + 1);
			
			// Remove top and left properties to avoid incorrect block positioning,
        	// set position to relative to keep blocks relative to scrollbar when scrolling
			block.css("top", "").css("left", "")
				.css("position", "relative").css("margin-left", offset + "px");
        }
        
        return {
        	apply: apply	
        };
	}

    var ArrayUtils = {
	
        contains: function (arr, obj) {
            var has = false;
            for (var i = 0; i < arr.length; i++) { if (arr[i] == obj) { has = true; } }
            return has;
        }
    };
    var DateUtils = {
    	
        daysBetween: function (start, end) {
            if (!start || !end) { return 0; }
            start = Date.parse(start); end = Date.parse(end);
            if (start.getYear() == 1901 || end.getYear() == 8099) { return 0; }
            var count = 0, date = start.clone();
            while (date.compareTo(end) == -1) { count = count + 1; date.addDays(1); }
            return count;
        },
        
        isWeekend: function (date) {
            return date.getDay() % 6 == 0;
        },

	/*	getBoundaryDatesFromData: function (data, minDays) {
			var minStart = new Date(); maxEnd = new Date();
			for (var i = 0; i < data.length; i++) {
				for (var j = 0; j < data[i].series.length; j++) {
					var start = Date.parse(data[i].series[j].start);
					var end = Date.parse(data[i].series[j].end)
					if (i == 0 && j == 0) { minStart = start; maxEnd = end; }
					if (minStart.compareTo(start) == 1) { minStart = start; }
					if (maxEnd.compareTo(end) == -1) { maxEnd = end; }
				}
			}
			
			// Insure that the width of the chart is at least the slide width to avoid empty
			// whitespace to the right of the grid
			if (DateUtils.daysBetween(minStart, maxEnd) < minDays) {
				maxEnd = minStart.clone().addDays(minDays);
			}
			
			return [minStart, maxEnd];
		}*/
    };

})(jQuery);