javascript: (function() {
    if (location.host.search('pgr21') == -1) {
        location.assign('https://pgr21.com/');
        return
    }
    var blacklist = new Array('ID1', 'ID2');
    var pcount = rcount = 0;
    var x = document.getElementsByTagName('tr');
    for (i in x) {
        if (x[i].align != 'center') continue;
        h = x[i].innerHTML;
        if (!!h) {
            for (s in blacklist) {
                if (h.search(blacklist[s]) != -1) {
                    x[i].style.visibility = 'hidden';
                    pcount++;
                    break
                }
            }
        }
    }
    var x = document.getElementsByTagName('td');
    for (i in x) {
        if (x[i].align != 'left') continue;
        h = x[i].innerHTML;
        if (!!h) {
            for (s in blacklist) {
                if (h.search(blacklist[s]) != -1) {
                    x[i].parentNode.parentNode.style.visibility = 'hidden';
                    rcount++;
                    break
                }
            }
        }
    }
    alert('Blacklist\'s Reply count = ' + rcount + '\nPost count =' + pcount)
})();