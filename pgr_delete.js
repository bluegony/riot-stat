javascript: (
function() {

    this.post = function (path, params, method) {
       method = method || "post";

       var form = document.createElement("form");
       form.setAttribute("method", method);
       form.setAttribute("action", path);

       for(var key in params) {
           if(params.hasOwnProperty(key)) {
               var hiddenField = document.createElement("input");
               hiddenField.setAttribute("type", "hidden");
               hiddenField.setAttribute("name", key);
               hiddenField.setAttribute("value", params[key]);

               form.appendChild(hiddenField);
           }
       }

       document.body.appendChild(form);
       form.submit();
    };

    if (location.host.search('pgr21') == -1) {
        location.assign('https://pgr21.com/');
        return
    }
    var boardId = location.search.split("id=")[1].split("&")[0];
    var number = location.search.split("&no=")[1].split("&")[0];
    var c_number;

    var blacklist = new Array( 'Theia', '푸른고니');
    var x = document.getElementsByClassName('ctName');

    for (i in x) {

        h = x[i].innerHTML;
        if (!!h) {
            for (s in blacklist) {
                if (h.search(blacklist[s]) != -1) {
                    var commentRoot = x[i].parentNode.parentNode.parentNode;
                    var realComment = commentRoot.getElementsByClassName("cmemo myArticle")[0];

                    c_number = x[i].parentNode.parentNode.parentNode.id.split("_")[1];

                    var cc=document.getElementById("commentContainer_"+c_number);

                    console.log("\n1. article number = "+number+", comment number = "+c_number+", comment nickname = "+blacklist[s]);
                    if(cc==null || cc.childElementCount==0) {
                        console.log("2. delete!!!!");
                        var path = "del_comment_ok.php";
                        var params = {id: boardId, no: number, c_no: c_number, confirm_del_text:'삭제합니다'};
                        post(path, params);

                    } else if(realComment.innerHTML.search('(수정됨)')!= -1 && realComment.innerHTML.length == 90) {
                        console.log("4. already modified, bypass");

                    } else {
                        alert("check and modify comment");
                        /*
                        console.log("3. modify!! realComment innerHTML length="+realComment.innerHTML.length);
                        var path = "modify_comment_ok.php";
                        var params = {id: boardId, no: number, c_no: c_number, memo:'.', floor_num:''};
                        post(path, params);*/

                    }
                    /*post(path, params);*/
                }
            }

        }
    }
}


)();