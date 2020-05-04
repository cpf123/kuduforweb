#!/usr/bin/env bash

id=$1
sparksubmitparam=$2
workdir=`cd ~ && pwd`
echo $workdir

#if [[  -d "${workdir}/com.jdb.ploandp.rtc" ]];then
#rm -rf ${workdir}/com.jdb.ploandp.rtc
#rm -rf ${workdir}/tmp
#mkdir ${workdir}/com.jdb.ploandp.rtc
#mkdir  ${workdir}/tmp
#else
#mkdir ${workdir}/com.jdb.ploandp.rtc
#mkdir  ${workdir}/tmp
#fi

spark-submit ${sparksubmitparam}
>  ${workdir}/tmp/${id}.log