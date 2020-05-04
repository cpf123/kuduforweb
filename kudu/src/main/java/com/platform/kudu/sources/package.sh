#!/usr/bin/env bash
id=$1
git=$2
filename=$3
#echo "hello"
#workdir= `dirname `dirname $0`` # 返回脚本上上级目录
#workdir=`dirname $0`

workdir=`cd ~ && pwd`
echo $workdir
if [[  -d "${workdir}/${filename}" ]];then
rm -rf ${workdir}/${filename}
rm -rf ${workdir}/tmp
mkdir ${workdir}/${filename}
mkdir  ${workdir}/tmp
else
mkdir ${workdir}/${filename}
mkdir  ${workdir}/tmp
fi

#sleep 10s
#echo "hello"> /tmp/${id}.log

git clone ${git} ${workdir}/${filename}
cd ${workdir}/${filename} &&  sbt clean assembly package >  ${workdir}/tmp/${id}.log