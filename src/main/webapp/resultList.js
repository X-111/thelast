function secBoard(n)
{
    for(i=1;i<3;i++)
    {
        eval("document.getElementById('cl0"+i+"').className='result03 result02'");
        eval("tbx0"+i+".style.display='none'");
    }
    eval("document.getElementById('cl0"+n+"').className='result03 result01'");
    eval("tbx0"+n+".style.display='block'");
}

// 显示、隐藏查询结果
function showResultBody()
{
    if ($("ResultBody").style.display=="none" || $("ResultBody").style.display=="" )
    {
        $("ResultBody").style.display="block";
    }
    else
    {
        $("ResultBody").style.display="none";
    }
}

function $() //document.getElementById
{
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++)
    {
        var element = arguments[i];
        if (typeof element == 'string')
        {
            element = document.getElementById(element);
        }
        if (arguments.length == 1)
        {
            return element;
        }
        elements.push(element);
    }
    return elements;
}

