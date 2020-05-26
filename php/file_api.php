<?php

// Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
// Please see the LICENSE file that should have been included as part of this package.
// Written by Isaac de la Pena <isaac@algonaut.us>

// php file_api.php

$sid = 'mySID';

$url = 'http://sofia.conexo.vc/xls';

$request = curl_init($url);

$request = curl_init($url);

$args = array(
    'sid' => $sid,
    'all_dates' => true,
    'xls' => new CurlFile('../Sofia_Metrics_Template.xlsx')
);

$jsonDataEncoded = json_encode($jsonData);

curl_setopt($request, CURLOPT_POST, true);

curl_setopt($request, CURLOPT_POSTFIELDS, $args);

$result = curl_exec($request);
$httpcode = curl_getinfo($request, CURLINFO_HTTP_CODE);
curl_close($request);

print(PHP_EOL . $httpcode . PHP_EOL);