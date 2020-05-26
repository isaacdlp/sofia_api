<?php

// Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
// Please see the LICENSE file that should have been included as part of this package.
// Written by Isaac de la Pena <isaac@algonaut.us>

// php data_api.php

$sid = 'mySID';

$url = 'http://sofia.conexo.vc/api';

$request = curl_init($url);

$jsonData = array(
    'sid' => $sid,
    'metrics' => array(
		'2020.01.01' => array(
			'10' => 3.605422555523507e-05,
			'11' => 35.7,
			'12' => 0.04579250190473688
		)
	)
);

$jsonDataEncoded = json_encode($jsonData);

curl_setopt($request, CURLOPT_POST, true);

curl_setopt($request, CURLOPT_POSTFIELDS, $jsonDataEncoded);

curl_setopt($request, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));

$result = curl_exec($request);
$httpcode = curl_getinfo($request, CURLINFO_HTTP_CODE);
curl_close($request);

print(PHP_EOL . $httpcode . PHP_EOL);