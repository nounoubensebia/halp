<%--
  Created by IntelliJ IDEA.
  User: Wail
  Date: 17/02/2020
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Template/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Template/js/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Template/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- Bootstrap tooltips -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Template/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Template/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Template/js/mdb.min.js"></script>
<!-- DataTables JS -->
<script src="${pageContext.request.contextPath}/Template/js/addons/datatables.min.js" type="text/javascript"></script>
<!-- DataTables Select JS -->
<script src="${pageContext.request.contextPath}/Template/js/addons/datatables-select.min.js" type="text/javascript"></script>
<!-- Initializations -->
<script type="text/javascript">
    // Animations initialization
    new WOW().init();

</script>

<script>
    $(function () {
        $('.material-tooltip-main').tooltip({
            template: '<div class="tooltip md-tooltip"><div class="tooltip-arrow md-arrow"></div><div class="tooltip-inner md-inner"></div></div>'
        });
    })
</script>

<script>
    $(document).ready(function () {
        $('#dtBasicExample').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/French.json"
            },
        });
    });
</script>
<script>
    $(function () {
        $('#datetimepicker7').datetimepicker({
            locale: 'fr',
            minDate: moment(),
            useCurrent: false,
        });
        $('#datetimepicker8').datetimepicker({
            useCurrent: false,
            locale: 'fr'
        });
        $("#datetimepicker7").on("change.datetimepicker", function (e) {
            $('#datetimepicker8').datetimepicker('minDate', e.date);
        });
        $("#datetimepicker8").on("change.datetimepicker", function (e) {
            $('#datetimepicker7').datetimepicker('maxDate', e.date);
        });
    });

</script>
