$(function () {
    $("#addNewPrj").click(function(){
        var prj = $("[name='prjName']");
        var path = $("[name='remotepath']");
        var msg = "";
        if(prj.val() == ""){
            msg = "工程名不能为空";
        }else if(path.val() == ""){
            msg = "工程路径不能为空";
        }
        if(msg != ""){
            alert(msg);
        }else{
           $("#prjForm").submit();
        }

    });
    $("button[project][action='getver']").click(function(e){
        // alert($(this).attr('project'));
        var prjName = $(this).attr('project');
        var sel = $("select[project='" + prjName + "_ver']");
        sel.html("");
        $.get("version/" + $(this).attr("project"), {}, function(data){
            // alert(data);
            var js = JSON.parse(data);
            // console.log(js);
            // alert(js);
            
            // console.log(sel);
            js.forEach(function(o){
                $(sel).append("<option value='" + o.identify + "'>" + o.identify + ":" + o.version + "[" + o.description + "]</option>");
            });

        });
    });
    $("button[project][action='compile']").click(function(e){
        alert("compile" + $(this).attr('project'));
    });
})